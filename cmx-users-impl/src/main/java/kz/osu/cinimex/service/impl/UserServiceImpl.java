package kz.osu.cinimex.service.impl;

import static java.lang.String.format;

import kz.osu.cinimex.dto.ChangeUserDto;
import kz.osu.cinimex.dto.UserDto;
import kz.osu.cinimex.entity.Role;
import kz.osu.cinimex.entity.SearchCriteria;
import kz.osu.cinimex.entity.User;
import kz.osu.cinimex.entity.specification.UserWithCriteriaSpecification;
import kz.osu.cinimex.exception.EntryExistsException;
import kz.osu.cinimex.exception.NotFoundException;
import kz.osu.cinimex.mapper.UserMapper;
import kz.osu.cinimex.repository.UserRepository;
import kz.osu.cinimex.service.RoleService;
import kz.osu.cinimex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;

    @Override
    @Transactional
    public void createUser(UserDto userDto) {
        if (userRepository.existsById(userDto.getLogin())) {
            throw new EntryExistsException("Указанный пользователь уже существует");
        }
        else {
            List<String> roleNames = new ArrayList<>(userDto.getRoles());
            if (roleNames.stream().allMatch(roleService::existsByName)) {
                List<Role> foundRoles = roleService.getRolesByName(roleNames);
                User user = userMapper.userDtoToUser(userDto);
                user.setRoles(foundRoles);
                userRepository.save(user);
            }
            else {
                throw new NotFoundException("Указанные роли отсутствуют в бд");
            }
        }
    }


    @Override
    @Transactional
    public UserDto changeUser(String login, ChangeUserDto changeUserDto) {
        User user = userRepository.findById(login)
                .orElseThrow(() -> new NotFoundException("Указанный пользователь не найден"));
        List<String> roleNames = new ArrayList<>(changeUserDto.getRoles());
        if (roleNames.stream().allMatch(roleService::existsByName)) {
            List<Role> foundRoles = roleService.getRolesByName(roleNames);
            User userNew = userMapper.changeUserDtoToUser(changeUserDto);
            user.setFirstName(userNew.getFirstName());
            user.setLastName(userNew.getLastName());
            user.setEmail(userNew.getEmail());
            user.setPhone(userNew.getPhone());
            user.setRoles(foundRoles);
            return userMapper.userToUserDto(userRepository.save(user));
        } else {
            throw new NotFoundException("Указанные роли отсутствуют в бд");
        }
    }


    @Override
    public UserDto getUserById(String login) {
        return userMapper.userToUserDto(userRepository.findById(login)
                .orElseThrow(() -> new NotFoundException(format("Пользователь с логином %s не найден", login))));
    }


    @Override
    public PagedModel<UserDto> getAllUsers(Pageable pageable, String lastName, String firstName, String login) {
        Specification<User> specificationLastName = new UserWithCriteriaSpecification(
                new SearchCriteria("lastName", "=", lastName));
        Specification<User> specificationFirstName = new UserWithCriteriaSpecification(
                new SearchCriteria("firstName", "=", firstName));
        Specification<User> specificationLogin = new UserWithCriteriaSpecification(
                new SearchCriteria("login", "=", login));
        return new PagedModel<>(
                userRepository.findAll(Specification.where(specificationLastName).and(specificationFirstName).and(specificationLogin), pageable)
                .map(userMapper::userToUserDto));
    }

    @Override
    @Transactional
    public void deleteUserById(String login) {
        userRepository.deleteById(login);
    }
}

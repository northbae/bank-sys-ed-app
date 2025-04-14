package kz.osu.cinimex.service.impl;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        List<Role> foundRoles = roleService.getRolesByName(userDto.getRoles());
        if (userDto.getRoles().size() != foundRoles.size()) {
            throw new NotFoundException("Указанные роли отсутствуют в бд");
        }
        User user = userMapper.userDtoToUser(userDto);
        user.setRoles(foundRoles);
        userRepository.save(user);

    }


    @Override
    @Transactional
    public Optional<UserDto> changeUser(String login, ChangeUserDto changeUserDto) {
        User user = userRepository.findById(login)
                .orElseThrow(() -> new NotFoundException("Указанный пользователь не найден"));
        List<Role> foundRoles = roleService.getRolesByName(changeUserDto.getRoles());
        if (changeUserDto.getRoles().size() != foundRoles.size()) {
            throw new NotFoundException("Указанные роли отсутствуют в бд");
        }
        User userNew = userMapper.changeUserDtoToUser(changeUserDto);
        user.setFirstName(userNew.getFirstName());
        user.setLastName(userNew.getLastName());
        user.setEmail(userNew.getEmail());
        user.setPhone(userNew.getPhone());
        user.setRoles(foundRoles);
        return Optional.of(userMapper.userToUserDto(userRepository.save(user)));
    }


    @Override
    public Optional<UserDto> getUserById(String login) {
        return userRepository.findById(login).map(userMapper::userToUserDto);
    }


    @Override
    public Page<UserDto> getAllUsers(Pageable pageable, String lastName, String firstName, String login) {
        Specification<User> specificationLastName = new UserWithCriteriaSpecification(
                new SearchCriteria("lastName", "=", lastName));
        Specification<User> specificationFirstName = new UserWithCriteriaSpecification(
                new SearchCriteria("firstName", "=", firstName));
        Specification<User> specificationLogin = new UserWithCriteriaSpecification(
                new SearchCriteria("login", "=", login));
        return userRepository.findAll(Specification.where(specificationLastName).and(specificationFirstName).and(specificationLogin), pageable)
                .map(userMapper::userToUserDto);
    }

    @Override
    @Transactional
    public void deleteUserById(String login) {
        userRepository.deleteById(login);
    }
}

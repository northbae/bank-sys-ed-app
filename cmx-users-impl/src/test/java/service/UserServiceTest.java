package service;

import kz.osu.cinimex.dto.ChangeUserDto;
import kz.osu.cinimex.dto.UserDto;
import kz.osu.cinimex.entity.Role;
import kz.osu.cinimex.entity.User;
import kz.osu.cinimex.exception.EntryExistsException;
import kz.osu.cinimex.mapper.UserMapper;
import kz.osu.cinimex.repository.UserRepository;
import kz.osu.cinimex.service.RoleService;
import kz.osu.cinimex.service.UserService;
import kz.osu.cinimex.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Spy
    private UserMapper userMapper;
    @Mock
    private RoleService roleService;
    @Mock
    private UserService mockUserService;

    @InjectMocks
    private UserServiceImpl userService;

    @DisplayName("Тест сохранения пользователя")
    @Test
    void handleCreateUser() {
        UserDto userDto = UserDto.builder()
                .login("user1")
                .lastName("Kr")
                .firstName("Lena")
                .email("ram@gmail.com")
                .phone("893311")
                .roles(Set.of("Admin"))
                .build();
        List<Role> roles = List.of(
                Role.builder()
                        .name("Admin")
                        .description("admin")
                        .build());
        User user = User.builder()
                .login("user1")
                .lastName("Kr")
                .firstName("Lena")
                .email("ram@gmail.com")
                .phone("893311")
                .roles(List.of(
                        Role.builder()
                                .name("Admin")
                                .description("admin")
                                .build()))
                .build();

        when(roleService.getRolesByName(any(Iterable.class))).thenReturn(roles);
        when(userMapper.userDtoToUser(any(UserDto.class))).thenReturn(user);

        userService.createUser(userDto);

        verify(userRepository, times(1)).save(same(user));
        verify(userMapper).userDtoToUser(same(userDto));
    }

    @DisplayName("Тест ошибки существующего пользователя при сохранении пользователя")
    @Test
    void handleEntryExistsExceptionWhenCreateUser() throws EntryExistsException{
        UserDto userDto = UserDto.builder()
                .login("user1")
                .lastName("Kr")
                .firstName("Lena")
                .email("ram@gmail.com")
                .phone("893311")
                .roles(Set.of("Admin"))
                .build();

        when(userRepository.existsById(any(String.class))).thenThrow(new EntryExistsException("Error"));

        assertThrows(EntryExistsException.class, () -> {
            userService.createUser(userDto);
        });
    }

    @DisplayName("Тест изменения пользователя")
    @Test
    void handleChangeUser() {
        String login = "user1";
        ChangeUserDto changeUserDto = ChangeUserDto.builder()
                .lastName("Kr")
                .firstName("Lena")
                .email("ram@gmail.com")
                .phone("893311")
                .roles(Set.of("Admin"))
                .build();
        User findUser = User.builder()
                .login("user1")
                .lastName("Kr")
                .firstName("Lena")
                .email("ram@gmail.com")
                .phone("893311")
                .roles(List.of(Role.builder().name("Admin").description("admin").build()))
                .build();
        UserDto expectedChangedUserDto = UserDto.builder()
                .login("user1")
                .lastName("Kr")
                .firstName("Lena")
                .email("ram@gmail.com")
                .phone("893311")
                .roles(Set.of("admin"))
                .build();
        List<Role> roles = List.of(Role.builder().name("Admin").description("admin").build());

        when(roleService.getRolesByName(any(Iterable.class))).thenReturn(roles);
        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(findUser));
        when(userRepository.save(any(User.class))).thenReturn(findUser);
        when(userMapper.userToUserDto(any(User.class))).thenReturn(expectedChangedUserDto);

        UserDto result = userService.changeUser(login, changeUserDto);

        verify(userRepository, times(1)).save(same(findUser));
        verify(userRepository).findById(same(login));
        verify(userMapper).userToUserDto(same(findUser));
        assertEquals(expectedChangedUserDto, result);
    }

    @DisplayName("Тест поиска пользователя по логину")
    @Test
    void handleGetUserById() {
        User expectedUser = mock(User.class);
        UserDto expectedUserDto = mock(UserDto.class);
        String login = "user1";

        when(userRepository.findById(any(String.class))).thenReturn(Optional.of(expectedUser));
        when(userMapper.userToUserDto(any(User.class))).thenReturn(expectedUserDto);

        UserDto result = userService.getUserById(login);

        assertEquals(expectedUserDto, result);
    }

    @DisplayName("Тест получения всех пользователей")
    @Test
    void handleGetAllUsers() {
        Pageable pageable = mock(Pageable.class);
        User findUsers = mock(User.class);
        Page<User> findUsersPage = new PageImpl<>(List.of(findUsers));
        UserDto expectedUsers = mock(UserDto.class);
        Page<UserDto> expectedUsersPage = new PageImpl<>(List.of(expectedUsers));
        String lastName = "";
        String firstName = "";
        String login = "";

        when(userRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(findUsersPage);
        when(userMapper.userToUserDto(any(User.class))).thenReturn(expectedUsers);

        Page<UserDto> result = userService.getAllUsers(pageable, lastName, firstName, login);

        assertThat(result).isNotNull();
        assertEquals(expectedUsersPage, result);
    }

    @DisplayName("Тест удаления пользователя")
    @Test
    void handleDeleteUserById() {
        String login = "user1";

        userService.deleteUserById(login);

        verify(userRepository, times(1)).deleteById(login);
    }
}
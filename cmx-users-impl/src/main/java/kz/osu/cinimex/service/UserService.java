package kz.osu.cinimex.service;

import kz.osu.cinimex.dto.ChangeUserDto;
import kz.osu.cinimex.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    void createUser(UserDto userDto);
    Optional<UserDto> changeUser(String login, ChangeUserDto changeUserDto);
    UserDto getUserById(String login);
    Page<UserDto> getAllUsers(Pageable pageable, String lastName, String firstName, String login);
    void deleteUserById(String login);
}
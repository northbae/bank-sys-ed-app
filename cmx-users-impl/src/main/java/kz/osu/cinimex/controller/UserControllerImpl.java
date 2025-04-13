package kz.osu.cinimex.controller;

import kz.osu.cinimex.dto.ChangeUserDto;
import kz.osu.cinimex.dto.UserDto;
import kz.osu.cinimex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{

    private final UserService userService;

    @Override
    public ResponseEntity<Void> createUser(UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDto> changeUser(String login, ChangeUserDto changeUserDto) {
        return ResponseEntity.ok(userService.changeUser(login, changeUserDto));
    }

    @Override
    public ResponseEntity<UserDto> getUserById(String login) {
        return ResponseEntity.ok(userService.getUserById(login));
    }

    @Override
    public ResponseEntity<PagedModel<UserDto>> getAllUsers(Pageable pageable, String lastName, String firstName, String login) {
        return ResponseEntity.ok(userService.getAllUsers(pageable, lastName, firstName, login));
    }

    @Override
    public ResponseEntity<Void> deleteUserById(String login) {
        userService.deleteUserById(login);
        return ResponseEntity.ok().build();
    }
}

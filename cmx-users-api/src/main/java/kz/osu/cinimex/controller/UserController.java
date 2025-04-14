package kz.osu.cinimex.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.osu.cinimex.dto.ChangeUserDto;
import kz.osu.cinimex.dto.ErrorMessageDto;
import kz.osu.cinimex.dto.RoleDto;
import kz.osu.cinimex.dto.UserDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping("api/users")
public interface UserController {
    @Operation(summary = "Добавить пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь добавлен в БД"),
            @ApiResponse(responseCode = "400", description = "Указанный пользователь уже существует / Указанные роли отсутствуют в бд /" +
                                        "<название поля> является обязательным полем",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessageDto.class)) })
    })
    @PostMapping
    ResponseEntity<Void> createUser(@Valid @RequestBody
                                       @Parameter(name = "user", description = "Информация о пользователе") UserDto userDto);

    @Operation(summary = "Изменить пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь изменен",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Указанные роли отсутствуют в бд / " +
                    "<название поля> является обязательным полем / Указанный пользователь не найден",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessageDto.class)) })
    })
    @PutMapping("/{login}")
    ResponseEntity<UserDto> changeUser(@PathVariable
                                    @Parameter(name = "login", description = "Логин пользователя") String login,
                                                @Valid @RequestBody
                                    @Parameter(name = "changeUser", description = "Информация об измененном пользователе") ChangeUserDto changeUserDto);

    @Operation(summary = "Получить данные пользователя по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Пользователь с логином <логин_пользователя> не найден",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ErrorMessageDto.class)) })
    })
    @GetMapping("/{login}")
    ResponseEntity<UserDto> getUserById(@PathVariable
                                         @Parameter(name = "login", description = "Логин пользователя") String login);

    @Operation(summary = "Найти всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователи найдены",
                    content = { @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RoleDto.class)) })
    })
    @GetMapping
    ResponseEntity<Page<UserDto>> getAllUsers(@ParameterObject Pageable pageable,
                                              @RequestParam(value = "lastName", required = false) String lastName,
                                              @RequestParam(value = "firstName", required = false) String firstName,
                                              @RequestParam(value = "login", required = false) String login);


    @Operation(summary = "Удалить пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь удален")
    })
    @DeleteMapping("/{login}")
    ResponseEntity<Void> deleteUserById(@PathVariable
                                       @Parameter(name = "login", description = "Логин пользователь") String login);
}

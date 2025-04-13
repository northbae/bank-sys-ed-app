package kz.osu.cinimex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "userDto", description = "Информация о пользователе")
public class UserDto {
    @Schema(description = "Логин пользователя")
    @NotBlank
    private String login;
    @Schema(description = "Фамилия пользователя")
    @NotBlank
    private String lastName;
    @Schema(description = "Имя пользователя")
    @NotBlank
    private String firstName;
    @Schema(description = "Email пользователя")
    @NotBlank
    private String email;
    @Schema(description = "Телефон пользователя")
    private String phone;
    @Schema(description = "Роли пользователя")
    @NotEmpty
    private Set<String> roles;
}

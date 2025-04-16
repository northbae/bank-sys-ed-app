package kz.osu.cinimex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "roleDto", description = "Информация о роли")
public class RoleDto {
    @Schema(description = "Название роли")
    @NotBlank
    private String name;
    @Schema(description = "Описание роли")
    private String description;
}

package kz.osu.cinimex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(name = "errorMessageDto", description = "Информация об ошибке")
public class ErrorMessageDto {
    @Schema(description = "Название ошибки")
    private String name;
    @Schema(description = "Описание ошибки")
    private String description;
    @Schema(description = "Время возникновения ошибки")
    private String timestamp;
}

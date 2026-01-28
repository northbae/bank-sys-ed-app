package kz.osu.cinimex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "accountDto", description = "Информация о созданном счете")
public class AccountDto {
    @Schema(description = "id счета")
    private UUID id;
    @Schema(description = "Логин владельца счета")
    private String login;
    @Schema(description = "Номер счета")
    private String number;
    @Schema(description = "Валюта счета")
    private AccountCurrencyDto currency;
    @Schema(description = "Текущий статус счета")
    private String status;
}

package kz.osu.cinimex.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "accountDto", description = "Детальная информация о счете")
public class DetailAccountDto {
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
    @Schema(description = "Баланс счета")
    private BigDecimal sum;
    @Schema(description = "Дата открытия счета")
    private LocalDate openDate;
    @Schema(description = "Является ли счет заблокированным")
    private Boolean isBlocked;
    @Schema(description = "Клиент решил отказаться от открытия счета")
    private Boolean isRejectedByUser;
}

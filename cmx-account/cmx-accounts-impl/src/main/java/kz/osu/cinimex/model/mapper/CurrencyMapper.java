package kz.osu.cinimex.model.mapper;

import kz.osu.cinimex.dto.AccountCurrencyDto;
import kz.osu.cinimex.model.enums.AccountCurrency;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrencyMapper {
    @Named("toCurrencyDto")
    AccountCurrencyDto currencyToCurrencyDto(AccountCurrency accountCurrency);
}

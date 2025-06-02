package kz.osu.cinimex.model.mapper;

import kz.osu.cinimex.dto.DetailAccountDto;
import kz.osu.cinimex.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {CurrencyMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    @Mapping(target = "login", source = "userLogin")
    @Mapping(target = "status", source = "currentStatus")
    DetailAccountDto accountToDetailAccountDto(Account account);
}

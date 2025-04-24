package kz.osu.cinimex.service;

import kz.osu.cinimex.dto.DetailAccountDto;
import kz.osu.cinimex.model.enums.AccountState;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    DetailAccountDto blockAccount(UUID accountId);

    DetailAccountDto closeAccount(UUID accountId);

    Boolean accountIsInState(UUID accountId, List<AccountState> states);
}

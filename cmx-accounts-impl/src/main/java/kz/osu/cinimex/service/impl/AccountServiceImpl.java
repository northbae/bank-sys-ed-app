package kz.osu.cinimex.service.impl;

import kz.osu.cinimex.dto.DetailAccountDto;
import kz.osu.cinimex.exception.NotFoundException;
import kz.osu.cinimex.exception.WrongAccountStateException;
import kz.osu.cinimex.lifecycle.AccountLifecycleService;
import kz.osu.cinimex.lifecycle.event.StartChecksEvent;
import kz.osu.cinimex.model.enums.AccountState;
import kz.osu.cinimex.model.mapper.AccountMapper;
import kz.osu.cinimex.repository.AccountRepository;
import kz.osu.cinimex.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountLifecycleService accountLifecycleService;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    private static final String ACC_NOT_FOUND = "Account not found";
    private static final String WRONG_ACC_STATE = "Account is not in the required status";

    @Override
    @Transactional
    public DetailAccountDto blockAccount(UUID accountId) {
        if (Boolean.FALSE.equals(accountIsInState(accountId, List.of(AccountState.OPENED))))
            throw new WrongAccountStateException(WRONG_ACC_STATE);
        String userLogin = accountRepository.findUserLoginById(accountId);
        accountLifecycleService.sendEvent(new StartChecksEvent(accountId, userLogin));
        return accountMapper.accountToDetailAccountDto(accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException(ACC_NOT_FOUND)));
    }

    @Override
    @Transactional
    public DetailAccountDto closeAccount(UUID accountId) {
        if (Boolean.FALSE.equals(accountIsInState(accountId, List.of(AccountState.OPENED))))
            throw new WrongAccountStateException(WRONG_ACC_STATE);
        String userLogin = accountRepository.findUserLoginById(accountId);
        accountLifecycleService.sendEvent(new StartChecksEvent(accountId, userLogin));
        return accountMapper.accountToDetailAccountDto(accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException(ACC_NOT_FOUND)));
    }

    @Override
    public Boolean accountIsInState(UUID accountId, List<AccountState> states) {
        return states.stream().anyMatch(accountState -> accountState.equals(
                accountRepository.findById(accountId).orElseThrow(() -> new NotFoundException(ACC_NOT_FOUND))
                        .getCurrentStatus()));
    }
}

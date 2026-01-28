package service;

import kz.osu.cinimex.dto.DetailAccountDto;
import kz.osu.cinimex.exception.WrongAccountStateException;
import kz.osu.cinimex.lifecycle.AccountLifecycleService;
import kz.osu.cinimex.lifecycle.event.StartChecksEvent;
import kz.osu.cinimex.model.entity.Account;
import kz.osu.cinimex.model.enums.AccountState;
import kz.osu.cinimex.model.mapper.AccountMapper;
import kz.osu.cinimex.repository.AccountRepository;
import kz.osu.cinimex.service.AccountService;
import kz.osu.cinimex.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BlockAccountTest {
    @Mock
    private AccountRepository accountRepository;
    @Spy
    private AccountMapper accountMapper;
    @Mock
    private AccountLifecycleService accountLifecycleService;
    @Mock
    private AccountService mockAccountService;
    @InjectMocks
    private AccountServiceImpl accountService;

    @DisplayName("Test block Account")
    @Test
    void handleBlockAccount() {
        UUID accountId = mock(UUID.class);
        String userLogin = "user";
        Account findAccount = mock(Account.class);
        AccountState correctState = AccountState.OPENED;
        Account blockedAccount = mock(Account.class);
        DetailAccountDto blockedAccountDto = mock(DetailAccountDto.class);

        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.of(findAccount)).thenReturn(Optional.of(blockedAccount));
        when(findAccount.getCurrentStatus()).thenReturn(correctState);
        when(accountRepository.findUserLoginById(any(UUID.class))).thenReturn(userLogin);
        when(accountMapper.accountToDetailAccountDto(any(Account.class))).thenReturn(blockedAccountDto);

        DetailAccountDto result = accountService.blockAccount(accountId);

        verify(accountLifecycleService, times(1)).sendEvent(any(StartChecksEvent.class));
        assertEquals(blockedAccountDto, result);
    }

    @DisplayName("test incorrect status throws WrongAccountStateException when blockAccount")
    @Test
    void handleWrongAccountStateExceptionWhenBlockAccount() {
        UUID accountId = mock(UUID.class);
        Account findAccount = mock(Account.class);
        AccountState wrongState = AccountState.BLOCKED;

        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.of(findAccount));
        when(findAccount.getCurrentStatus()).thenReturn(wrongState);

        assertThrows(WrongAccountStateException.class, () -> {
            accountService.blockAccount(accountId);
        });
    }
}

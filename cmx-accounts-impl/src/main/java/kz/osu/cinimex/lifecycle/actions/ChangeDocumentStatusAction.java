package kz.osu.cinimex.lifecycle.actions;

import kz.osu.cinimex.entity.Account;
import kz.osu.cinimex.entity.AccountEvent;
import kz.osu.cinimex.entity.AccountState;
import kz.osu.cinimex.entity.AccountStatusHistory;
import kz.osu.cinimex.repository.AccountRepository;
import kz.osu.cinimex.repository.AccountStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.event.spi.AbstractEvent;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@RequiredArgsConstructor
public class ChangeDocumentStatusAction implements Action<AccountState, AccountEvent> {

    private final AccountRepository accountRepository;
    private final AccountStatusHistoryRepository accountStatusHistoryRepository;

    @Override
    public void execute(final StateContext<AccountState, AccountEvent> context) {
        AccountState newStatus = context.getTransition().getTarget().getId();
        final AbstractEvent event = context.getExtendedState().get("event", AbstractEvent.class);
        Optional<Account> optAccount = accountRepository.findById(event.getAccountId());
        if (optAccount.isPresent()) {
            Account account = optAccount.get();
            account.setCurrentStatus(newStatus);
            accountRepository.save(account);
            accountStatusHistoryRepository.save(new AccountStatusHistory()
                    .setStatus(newStatus)
                    .setCreatedAt(LocalDateTime.now(ZoneOffset.UTC))
                    .setCreatedBy("system")
                    .setAccount(account)
            );
        }
    }
}

package kz.osu.cinimex.lifecycle.actions;

import kz.osu.cinimex.model.enums.AccountEvent;
import kz.osu.cinimex.model.enums.AccountState;
import kz.osu.cinimex.model.entity.AccountStatusHistory;
import kz.osu.cinimex.lifecycle.event.AbstractEvent;
import kz.osu.cinimex.repository.AccountRepository;
import kz.osu.cinimex.repository.AccountStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChangeDocumentStatusAction implements Action<AccountState, AccountEvent> {

    private final AccountRepository accountRepository;
    private final AccountStatusHistoryRepository accountStatusHistoryRepository;

    @Override
    public void execute(final StateContext<AccountState, AccountEvent> context) {
        AccountState newStatus = context.getTransition().getTarget().getId();
        final AbstractEvent event = context.getExtendedState().get("event", AbstractEvent.class);
        accountRepository.findById(event.getAccountId())
                .ifPresent(account -> {
                    account.setCurrentStatus(newStatus);
                    accountRepository.save(account);
                    accountStatusHistoryRepository.save(new AccountStatusHistory()
                            .setStatus(newStatus)
                            .setCreatedAt(LocalDateTime.now(ZoneOffset.UTC))
                            .setCreatedBy(event.getInitiator())
                            .setAccount(account));
                });
    }
}

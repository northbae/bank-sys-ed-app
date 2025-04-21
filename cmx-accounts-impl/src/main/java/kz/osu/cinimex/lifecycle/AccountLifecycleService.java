package kz.osu.cinimex.lifecycle;

import static java.lang.String.format;

import java.util.Map;
import kz.osu.cinimex.exception.LifecycleException;
import kz.osu.cinimex.lifecycle.event.AbstractEvent;
import kz.osu.cinimex.model.enums.AccountEvent;
import kz.osu.cinimex.model.enums.AccountState;
import kz.osu.cinimex.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.ExtendedState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountLifecycleService {

    private final StateMachineFactory<AccountState, AccountEvent> factory;
    private final AccountRepository accountRepository;

    private static final String CANT_SEND_EVENT = "State machine can't send event %s for orderId=%s. Probably transition not found.";
    private static final String OTHER_ERRORS = "There are errors in state machine after transition %s. Entity orderId=%s";

    public void sendEvent(AbstractEvent event) {
        // Получаем текущий статус счета, начальный статус-заглушка - NONE
        final AccountState orderStatus = AccountState.valueOf(
                accountRepository.findCurrentStatusById(event.getAccountId())
                        .orElseThrow(() -> new IllegalStateException("Account status not found")));
        // Получаем экземпляр SM с нужным статусом
        final StateMachine<AccountState, AccountEvent> stateMachine = getStateMachine(orderStatus);
        // Помещаем событие в контекст
        stateMachine.getExtendedState().getVariables().put("event", event);
        // Отправка события и обработка ошибок
        if (!stateMachine.sendEvent(event.getEvent())) {
            exceptionHandling(stateMachine, event, CANT_SEND_EVENT);
        } else if (stateMachine.hasStateMachineError()) {
            exceptionHandling(stateMachine, event, OTHER_ERRORS);
        }
        // Останавливаем экземпляр
        stateMachine.stop();
    }

    private StateMachine<AccountState, AccountEvent> getStateMachine(AccountState initialState) {
        final StateMachine<AccountState, AccountEvent> machine = factory.getStateMachine();
        machine.getStateMachineAccessor().withRegion().resetStateMachine(
                new DefaultStateMachineContext(initialState, (Object) null, (Map) null,
                        (ExtendedState) null));
        return machine;
    }

    private void exceptionHandling(StateMachine<AccountState, AccountEvent> stateMachine,
            AbstractEvent event, String errorText) {
        stateMachine.stop();
        final String error = format(errorText, event, event.getAccountId());
        log.error(error);
        throw new LifecycleException(error);
    }
}

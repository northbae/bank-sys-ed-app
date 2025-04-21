package kz.osu.cinimex.lifecycle;

import kz.osu.cinimex.model.enums.AccountEvent;
import kz.osu.cinimex.model.enums.AccountState;
import kz.osu.cinimex.lifecycle.actions.ChangeDocumentStatusAction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@RequiredArgsConstructor
@EnableStateMachineFactory
public class LifecycleConfig extends StateMachineConfigurerAdapter<AccountState, AccountEvent> {

    private final ChangeDocumentStatusAction changeDocumentStatusAction;

    @Override
    public void configure(final StateMachineStateConfigurer<AccountState, AccountEvent> states) throws Exception {
        states
                .withStates()
                .initial(AccountState.NONE)
                .end(AccountState.REQUEST_REJECTED)
                .end(AccountState.REQUEST_REJECTED_BY_CFT)
                .end(AccountState.BLOCKED)
                .end(AccountState.CLOSED)
                .states(EnumSet.allOf(AccountState.class));
    }

    @Override
    public void configure(final StateMachineConfigurationConfigurer<AccountState, AccountEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
    }

    @Override
    public void configure(final StateMachineTransitionConfigurer<AccountState, AccountEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(AccountState.NONE)
                .target(AccountState.REQUESTED_BY_USER)
                .event(AccountEvent.CREATE)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.REQUESTED_BY_USER)
                .target(AccountState.REQUEST_SENT_TO_CFT)
                .event(AccountEvent.START_CHECKS)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.REQUEST_SENT_TO_CFT)
                .target(AccountState.CHECKS_STARTED)
                .event(AccountEvent.REQUEST_RECEIVED)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_STARTED)
                .target(AccountState.CHECKS_COMPLETED)
                .event(AccountEvent.SUCCESS)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_COMPLETED)
                .target(AccountState.OPENED)
                .event(AccountEvent.OPEN)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_COMPLETED)
                .target(AccountState.REQUEST_REJECTED)
                .event(AccountEvent.USER_REJECT)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_COMPLETED)
                .target(AccountState.REQUEST_SENT_TO_CFT)
                .event(AccountEvent.CONTINUE)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_STARTED)
                .target(AccountState.CHECKS_FAILED)
                .event(AccountEvent.FAIL)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_FAILED)
                .target(AccountState.REQUEST_SENT_TO_CFT)
                .event(AccountEvent.START_CHECKS)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_FAILED)
                .target(AccountState.REQUEST_REJECTED)
                .event(AccountEvent.USER_REJECT)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_STARTED)
                .target(AccountState.REQUEST_REJECTED_BY_CFT)
                .event(AccountEvent.REJECT)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_STARTED)
                .target(AccountState.BLOCKED)
                .event(AccountEvent.BLOCK)
                .action(changeDocumentStatusAction)

                .and()
                .withExternal()
                .source(AccountState.CHECKS_STARTED)
                .target(AccountState.CLOSED)
                .event(AccountEvent.CLOSE)
                .action(changeDocumentStatusAction);
    }
}

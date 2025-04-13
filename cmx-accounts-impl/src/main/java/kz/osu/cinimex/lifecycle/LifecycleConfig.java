package kz.osu.cinimex.lifecycle;

import kz.osu.cinimex.actions.ChangeDocumentStatusAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class LifecycleConfig extends StateMachineConfigurerAdapter<State, Event> {

    @Override
    public void configure(final StateMachineStateConfigurer<State, Event> states) throws Exception {
        states
                .withStates()
                .initial(State.REQUESTED_BY_USER)
                .end(State.REQUEST_REJECTED)
                .end(State.REQUEST_REJECTED_BY_CFT)
                .end(State.BLOCKED)
                .end(State.CLOSED)
                .states(EnumSet.allOf(State.class));
    }

    @Override
    public void configure(final StateMachineConfigurationConfigurer<State, Event> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true);
                //.listener(new StateMachineApplicationListener());
    }

    @Override
    public void configure(final StateMachineTransitionConfigurer<State, Event> transitions) throws Exception {
        transitions
                .withExternal()
                .source(State.REQUESTED_BY_USER)
                .target(State.REQUEST_SENT_TO_CFT)
                .event(Event.START_CHECKS)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.REQUEST_SENT_TO_CFT)
                .target(State.CHECKS_STARTED)
                .event(Event.REQUEST_RECEIVED)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_STARTED)
                .target(State.CHECKS_COMPLETED)
                .event(Event.SUCCESS)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_COMPLETED)
                .target(State.OPENED)
                .event(Event.OPEN)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_COMPLETED)
                .target(State.REQUEST_REJECTED)
                .event(Event.USER_REJECT)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_COMPLETED)
                .target(State.REQUEST_SENT_TO_CFT)
                .event(Event.CONTINUE)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_STARTED)
                .target(State.CHECKS_FAILED)
                .event(Event.FAIL)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_FAILED)
                .target(State.REQUEST_SENT_TO_CFT)
                .event(Event.START_CHECKS)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_FAILED)
                .target(State.REQUEST_REJECTED)
                .event(Event.USER_REJECT)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_STARTED)
                .target(State.REQUEST_REJECTED_BY_CFT)
                .event(Event.REJECT)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_STARTED)
                .target(State.BLOCKED)
                .event(Event.BLOCK)
                .action(changeDocumentStatusAction())

                .and()
                .withExternal()
                .source(State.CHECKS_STARTED)
                .target(State.CLOSED)
                .event(Event.CLOSE)
                .action(changeDocumentStatusAction());
    }

    @Bean
    public Action<State, Event> changeDocumentStatusAction() {
        return new ChangeDocumentStatusAction();
    }
}

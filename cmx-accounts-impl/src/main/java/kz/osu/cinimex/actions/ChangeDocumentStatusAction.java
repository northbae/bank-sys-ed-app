package kz.osu.cinimex.actions;

import kz.osu.cinimex.lifecycle.Event;
import kz.osu.cinimex.lifecycle.State;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class ChangeDocumentStatusAction implements Action<State, Event> {

    @Override
    public void execute(final StateContext<State, Event> context) {
        System.out.println("Переход в статус " + context.getTarget().getId());
    }
}

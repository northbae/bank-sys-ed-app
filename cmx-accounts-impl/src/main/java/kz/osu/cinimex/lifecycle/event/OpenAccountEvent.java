package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.OPEN;
import static kz.osu.cinimex.model.enums.StatusChangeInitiator.SYSTEM;

import java.util.UUID;

public class OpenAccountEvent extends AbstractEvent{

    public OpenAccountEvent(UUID accountId) {
        super(accountId, OPEN, SYSTEM.name());
    }
}

package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.REQUEST_RECEIVED;
import static kz.osu.cinimex.model.enums.StatusChangeInitiator.SYSTEM;

import java.util.UUID;

public class ChecksStartedEvent extends AbstractEvent{

    public ChecksStartedEvent(UUID accountId) {
        super(accountId, REQUEST_RECEIVED, SYSTEM.name());
    }
}

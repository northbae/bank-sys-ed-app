package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.CONTINUE;
import static kz.osu.cinimex.model.enums.StatusChangeInitiator.SYSTEM;

import java.util.UUID;

public class ContinueChecksEvent extends AbstractEvent{

    public ContinueChecksEvent(UUID accountId) {
        super(accountId, CONTINUE, SYSTEM.name());
    }
}

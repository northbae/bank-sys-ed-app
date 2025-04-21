package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.FAIL;
import static kz.osu.cinimex.model.enums.StatusChangeInitiator.CFT;

import java.util.UUID;

public class CheckFailedEvent extends AbstractEvent{

    public CheckFailedEvent(UUID accountId) {
        super(accountId, FAIL, CFT.name());
    }
}

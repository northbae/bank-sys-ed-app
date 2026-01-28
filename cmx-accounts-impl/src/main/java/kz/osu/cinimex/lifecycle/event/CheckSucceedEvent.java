package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.SUCCESS;
import static kz.osu.cinimex.model.enums.StatusChangeInitiator.SYSTEM;

import java.util.UUID;

public class CheckSucceedEvent extends AbstractEvent{

    public CheckSucceedEvent(UUID accountId) {
        super(accountId, SUCCESS, SYSTEM.name());
    }
}

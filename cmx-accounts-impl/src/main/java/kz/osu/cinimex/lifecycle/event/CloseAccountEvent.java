package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.BLOCK;

import java.util.UUID;

public class CloseAccountEvent extends AbstractEvent{

    public CloseAccountEvent(UUID accountId, String initiator) {
        super(accountId, BLOCK, initiator);
    }
}

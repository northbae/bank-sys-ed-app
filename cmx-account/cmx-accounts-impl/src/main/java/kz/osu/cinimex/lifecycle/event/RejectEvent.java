package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.REJECT;
import static kz.osu.cinimex.model.enums.StatusChangeInitiator.CFT;

import java.util.UUID;

public class RejectEvent extends AbstractEvent{

    public RejectEvent(UUID accountId) {
        super(accountId, REJECT, CFT.name());
    }
}

package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.USER_REJECT;

import java.util.UUID;

public class UserRejectEvent extends AbstractEvent{

    public UserRejectEvent(UUID accountId, String initiator) {
        super(accountId, USER_REJECT, initiator);
    }
}

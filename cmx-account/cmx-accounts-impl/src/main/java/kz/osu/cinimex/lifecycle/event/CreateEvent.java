package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.CREATE;

import java.util.UUID;

public class CreateEvent extends AbstractEvent{

    public CreateEvent(UUID accountId, String initiator) {
        super(accountId, CREATE, initiator);
    }
}

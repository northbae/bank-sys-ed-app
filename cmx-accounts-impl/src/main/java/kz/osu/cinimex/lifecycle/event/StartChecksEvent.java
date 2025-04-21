package kz.osu.cinimex.lifecycle.event;

import static kz.osu.cinimex.model.enums.AccountEvent.START_CHECKS;

import java.util.UUID;

public class StartChecksEvent extends AbstractEvent{

    public StartChecksEvent(UUID accountId, String initiator) {
        super(accountId, START_CHECKS, initiator);
    }
}

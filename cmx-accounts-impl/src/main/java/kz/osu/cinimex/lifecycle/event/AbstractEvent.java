package kz.osu.cinimex.lifecycle.event;

import java.util.UUID;
import kz.osu.cinimex.model.enums.AccountEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AbstractEvent {
    private UUID accountId;
    private AccountEvent event;
    private String initiator;
}

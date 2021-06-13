package me.ioannisioannou.transactional.outbox.producer.events;

import lombok.Builder;
import lombok.Data;
import me.ioannisioannou.transactional.outbox.events.DomainEvent;

@Data
@Builder
public class EnrichedDomainEvent<T extends DomainEvent> {
    private String aggregateType;
    private String aggregateId;
    private T domainEvent;

    public String getDomainEventType() {
        return domainEvent.getClass().getSimpleName();
    }
}

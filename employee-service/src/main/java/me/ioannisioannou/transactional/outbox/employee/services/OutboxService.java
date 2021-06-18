package me.ioannisioannou.transactional.outbox.employee.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import me.ioannisioannou.transactional.outbox.employee.entities.Outbox;
import me.ioannisioannou.transactional.outbox.employee.events.EnrichedDomainEvent;
import me.ioannisioannou.transactional.outbox.employee.repositories.OutboxRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    @EventListener
    public void handleEnrichedDomainEvent(EnrichedDomainEvent<?> event) {
        outboxRepository.save(Outbox.builder()
                .aggregateId(event.getAggregateId())
                .aggregateType(event.getAggregateType())
                .eventType(event.getDomainEventType())
                .payload(objectMapper.convertValue(event.getDomainEvent(), JsonNode.class))
                .build());
    }
}

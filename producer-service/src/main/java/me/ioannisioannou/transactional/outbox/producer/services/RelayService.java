package me.ioannisioannou.transactional.outbox.producer.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import lombok.RequiredArgsConstructor;
import me.ioannisioannou.transactional.outbox.events.DomainEvent;
import me.ioannisioannou.transactional.outbox.producer.entities.Outbox;
import me.ioannisioannou.transactional.outbox.producer.repositories.OutboxRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class RelayService {

    private final OutboxRepository outboxRepository;
    private final NotificationMessagingTemplate notificationMessagingTemplate;
    private final ObjectMapper objectMapper;

    @Value("${relay.sns-topic}")
    private String snsTopicName;

    @Scheduled(fixedDelayString = "${relay.polling-ms}")
    public void relayEvents() {
        List<Outbox> outboxEvent = outboxRepository.findTop100ByOrderByCreatedAtAsc();
        outboxEvent.forEach(event -> {
            Map<String, Object> headers = Map.of("eventType", objectMapper.convertValue(event.getPayload(), DomainEvent.class).getClass().getSimpleName());
            notificationMessagingTemplate
                    .convertAndSend(snsTopicName, event.getPayload(), headers);
            outboxRepository.delete(event);
        });
    }
}

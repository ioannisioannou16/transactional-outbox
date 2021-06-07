package me.ioannisioannou.transactional.outbox.consumer.services;

import io.awspring.cloud.messaging.config.annotation.NotificationMessage;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import me.ioannisioannou.transactional.outbox.events.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @SqsListener(value = "${notifier.input-event-queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveQueueMessages(@NotificationMessage DomainEvent domainEvent) {
        logger.info("Received domain event {}", domainEvent);
    }
}

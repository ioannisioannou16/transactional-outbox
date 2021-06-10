package me.ioannisioannou.transactional.outbox.consumer;

import io.awspring.cloud.messaging.config.annotation.NotificationMessage;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import me.ioannisioannou.transactional.outbox.events.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@SqsListener(value = "${input_queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void receiveQueueMessages(@NotificationMessage DomainEvent domainEvent) {
		logger.info("Received domain event {}", domainEvent);
	}
}

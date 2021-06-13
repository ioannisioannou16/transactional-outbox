package me.ioannisioannou.transactional.outbox.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.config.annotation.NotificationMessage;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import me.ioannisioannou.transactional.outbox.events.DomainEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@SpringBootApplication
public class ConsumerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	@Bean
	public MappingJackson2MessageConverter mappingJackson2MessageConverter(ObjectMapper objectMapper) {
		MappingJackson2MessageConverter jackson2MessageConverter = new MappingJackson2MessageConverter();
		jackson2MessageConverter.setObjectMapper(objectMapper);
		return jackson2MessageConverter;
	}

	@SqsListener(value = "${input_queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	public void receiveQueueMessages(@NotificationMessage DomainEvent domainEvent) {
		logger.info("Received domain event {}", domainEvent);
	}
}

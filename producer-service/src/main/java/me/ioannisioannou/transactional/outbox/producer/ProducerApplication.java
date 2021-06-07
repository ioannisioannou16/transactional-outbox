package me.ioannisioannou.transactional.outbox.producer;

import com.amazonaws.services.sns.AmazonSNS;
import io.awspring.cloud.messaging.core.NotificationMessagingTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Bean
	public NotificationMessagingTemplate getNotificationMessagingTemplate(AmazonSNS amazonSNS) {
		return new NotificationMessagingTemplate(amazonSNS);
	}
}

package me.ioannisioannou.transactional.outbox.producer.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.RequiredArgsConstructor;
import me.ioannisioannou.transactional.outbox.producer.entities.Outbox;
import me.ioannisioannou.transactional.outbox.producer.repositories.OutboxRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class CDCService {

    private final OutboxRepository outboxRepository;
    private final AmazonSNS amazonSNS;

    @Value("${cdc.sns_topic}")
    private String snsTopic;
    @Value("${cdc.batch_size}")
    private int batchSize;

    @Scheduled(fixedDelayString = "${cdc.polling_ms}")
    public void forwardEventsToSNS() {
        List<Outbox> entities = outboxRepository.findAllByOrderByIdAsc(Pageable.ofSize(batchSize)).toList();
        entities.forEach(entity -> amazonSNS.publish(new PublishRequest()
                .withTopicArn(snsTopic)
                .withMessage(entity.getPayload().toString())
                .withMessageGroupId(String.format("%s-%s", entity.getAggregateType(), entity.getAggregateId()))
                .withMessageAttributes(Map.of("eventType", new MessageAttributeValue()
                        .withDataType("String").withStringValue(entity.getEventType())))));
        outboxRepository.deleteAllInBatch(entities);
    }
}

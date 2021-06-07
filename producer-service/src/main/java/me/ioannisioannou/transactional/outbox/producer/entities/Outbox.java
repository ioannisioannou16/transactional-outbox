package me.ioannisioannou.transactional.outbox.producer.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "outbox", indexes = @Index(columnList = "createdAt"))
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(typeClass = JsonType.class, defaultForType = JsonNode.class)
public class Outbox {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(columnDefinition = "json")
    private JsonNode payload;

    @CreationTimestamp
    private Date createdAt;
}

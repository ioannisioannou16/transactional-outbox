package me.ioannisioannou.transactional.outbox.producer.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "outbox")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(typeClass = JsonType.class, defaultForType = JsonNode.class)
public class Outbox {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outbox_seq")
    private Long id;

    @Column(columnDefinition = "json")
    private JsonNode payload;

    @CreationTimestamp
    private Date createdAt;
}

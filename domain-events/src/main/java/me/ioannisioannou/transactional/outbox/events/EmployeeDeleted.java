package me.ioannisioannou.transactional.outbox.events;

import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDeleted extends DomainEvent {
    private UUID id;
}

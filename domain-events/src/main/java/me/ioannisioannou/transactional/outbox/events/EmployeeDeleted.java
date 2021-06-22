package me.ioannisioannou.transactional.outbox.events;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeDeleted extends DomainEvent {
    private UUID id;
}

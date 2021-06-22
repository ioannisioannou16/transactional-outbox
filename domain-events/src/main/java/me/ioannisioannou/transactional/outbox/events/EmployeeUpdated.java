package me.ioannisioannou.transactional.outbox.events;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeUpdated extends DomainEvent {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}

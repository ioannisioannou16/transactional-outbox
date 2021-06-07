package me.ioannisioannou.transactional.outbox.events;

import lombok.*;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreated extends DomainEvent {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}

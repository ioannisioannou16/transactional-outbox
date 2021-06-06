package me.ioannisioannou.transactional.outbox.events;

import lombok.*;
import me.ioannisioannou.transactional.outbox.entities.Employee;

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

    public static EmployeeCreated of(Employee employee) {
        return EmployeeCreated.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}

package me.ioannisioannou.transactional.outbox.events;

import lombok.*;
import me.ioannisioannou.transactional.outbox.entities.Employee;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdated extends DomainEvent {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    public static EmployeeUpdated of(Employee employee) {
        return EmployeeUpdated.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}

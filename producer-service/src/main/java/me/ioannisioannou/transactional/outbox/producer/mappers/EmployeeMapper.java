package me.ioannisioannou.transactional.outbox.producer.mappers;

import me.ioannisioannou.transactional.outbox.events.EmployeeCreated;
import me.ioannisioannou.transactional.outbox.events.EmployeeDeleted;
import me.ioannisioannou.transactional.outbox.events.EmployeeUpdated;
import me.ioannisioannou.transactional.outbox.producer.entities.Employee;

import java.util.UUID;

public class EmployeeMapper {

    public static EmployeeCreated toEmployeeCreatedDomainEvent(Employee employee) {
        return EmployeeCreated.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }

    public static EmployeeUpdated toEmployeeUpdatedDomainEvent(Employee employee) {
        return EmployeeUpdated.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }

    public static EmployeeDeleted toEmployeeDeletedDomainEvent(UUID id) {
        return EmployeeDeleted.builder()
                .id(id)
                .build();
    }
}

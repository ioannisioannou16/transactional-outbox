package me.ioannisioannou.transactional.outbox.producer.mappers;

import me.ioannisioannou.transactional.outbox.events.EmployeeCreated;
import me.ioannisioannou.transactional.outbox.events.EmployeeDeleted;
import me.ioannisioannou.transactional.outbox.events.EmployeeUpdated;
import me.ioannisioannou.transactional.outbox.producer.dtos.CreateEmployeeRequest;
import me.ioannisioannou.transactional.outbox.producer.dtos.CreateEmployeeResponse;
import me.ioannisioannou.transactional.outbox.producer.dtos.UpdateEmployeeRequest;
import me.ioannisioannou.transactional.outbox.producer.dtos.UpdateEmployeeResponse;
import me.ioannisioannou.transactional.outbox.producer.entities.Employee;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmployeeMapper {

    public Employee toCreateEmployeeEntity(CreateEmployeeRequest createEmployeeRequest) {
        return Employee.builder()
                .firstName(createEmployeeRequest.getFirstName())
                .lastName(createEmployeeRequest.getLastName())
                .email(createEmployeeRequest.getEmail())
                .build();
    }

    public CreateEmployeeResponse toCreateEmployeeResponse(Employee employee) {
        return CreateEmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }

    public Employee toUpdateEmployeeEntity(UpdateEmployeeRequest updateEmployeeRequest) {
        return Employee.builder()
                .firstName(updateEmployeeRequest.getFirstName())
                .lastName(updateEmployeeRequest.getLastName())
                .email(updateEmployeeRequest.getEmail())
                .build();
    }

    public UpdateEmployeeResponse toUpdateEmployeeResponse(Employee employee) {
        return UpdateEmployeeResponse.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }

    public EmployeeCreated toEmployeeCreatedDomainEvent(Employee employee) {
        return EmployeeCreated.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }

    public EmployeeUpdated toEmployeeUpdatedDomainEvent(Employee employee) {
        return EmployeeUpdated.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }

    public EmployeeDeleted toEmployeeDeletedDomainEvent(UUID id) {
        return EmployeeDeleted.builder()
                .id(id)
                .build();
    }
}

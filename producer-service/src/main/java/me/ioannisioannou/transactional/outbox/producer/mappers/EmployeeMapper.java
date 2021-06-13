package me.ioannisioannou.transactional.outbox.producer.mappers;

import me.ioannisioannou.transactional.outbox.events.EmployeeCreated;
import me.ioannisioannou.transactional.outbox.events.EmployeeDeleted;
import me.ioannisioannou.transactional.outbox.events.EmployeeUpdated;
import me.ioannisioannou.transactional.outbox.producer.dtos.CreateEmployeeRequest;
import me.ioannisioannou.transactional.outbox.producer.dtos.CreateEmployeeResponse;
import me.ioannisioannou.transactional.outbox.producer.dtos.UpdateEmployeeRequest;
import me.ioannisioannou.transactional.outbox.producer.dtos.UpdateEmployeeResponse;
import me.ioannisioannou.transactional.outbox.producer.entities.Employee;
import me.ioannisioannou.transactional.outbox.producer.events.EnrichedDomainEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmployeeMapper {

    private static final String EMPLOYEE_AGGREGATE_TYPE = Employee.class.getSimpleName();

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

    public EnrichedDomainEvent<EmployeeCreated> toEmployeeCreatedDomainEvent(Employee employee) {
        return EnrichedDomainEvent.<EmployeeCreated>builder()
                .aggregateType(EMPLOYEE_AGGREGATE_TYPE)
                .aggregateId(employee.getId().toString())
                .domainEvent(EmployeeCreated.builder()
                        .id(employee.getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .build())
                .build();
    }

    public EnrichedDomainEvent<EmployeeUpdated> toEmployeeUpdatedDomainEvent(Employee employee) {
        return EnrichedDomainEvent.<EmployeeUpdated>builder()
                .aggregateType(EMPLOYEE_AGGREGATE_TYPE)
                .aggregateId(employee.getId().toString())
                .domainEvent(EmployeeUpdated.builder()
                        .id(employee.getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .build())
                .build();
    }

    public EnrichedDomainEvent<EmployeeDeleted> toEmployeeDeletedDomainEvent(UUID id) {
        return EnrichedDomainEvent.<EmployeeDeleted>builder()
                .aggregateType(EMPLOYEE_AGGREGATE_TYPE)
                .aggregateId(id.toString())
                .domainEvent(EmployeeDeleted.builder().id(id).build())
                .build();
    }
}

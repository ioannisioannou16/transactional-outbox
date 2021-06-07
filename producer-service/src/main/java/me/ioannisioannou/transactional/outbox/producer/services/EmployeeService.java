package me.ioannisioannou.transactional.outbox.producer.services;

import lombok.RequiredArgsConstructor;
import me.ioannisioannou.transactional.outbox.producer.entities.Employee;
import me.ioannisioannou.transactional.outbox.producer.exceptions.EmployeeDoesNotExistException;
import me.ioannisioannou.transactional.outbox.producer.mappers.EmployeeMapper;
import me.ioannisioannou.transactional.outbox.producer.repositories.EmployeeRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Employee create(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        eventPublisher.publishEvent(EmployeeMapper.toEmployeeCreatedDomainEvent(employee));
        return savedEmployee;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee get(UUID id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeDoesNotExistException(id));
    }

    public Employee update(UUID id, Employee employee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    existingEmployee.updateTo(employee);
                    Employee updatedEmployee = employeeRepository.save(existingEmployee);
                    eventPublisher.publishEvent(EmployeeMapper.toEmployeeUpdatedDomainEvent(employee));
                    return updatedEmployee;
                })
                .orElseThrow(() -> new EmployeeDoesNotExistException(id));
    }

    public void delete(UUID id) {
        try {
            employeeRepository.deleteById(id);
            eventPublisher.publishEvent(EmployeeMapper.toEmployeeDeletedDomainEvent(id));
        } catch (EmptyResultDataAccessException ignored) {
            throw new EmployeeDoesNotExistException(id);
        }
    }
}
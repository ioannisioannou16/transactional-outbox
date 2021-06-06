package me.ioannisioannou.transactional.outbox.services;

import lombok.RequiredArgsConstructor;
import me.ioannisioannou.transactional.outbox.entities.Employee;
import me.ioannisioannou.transactional.outbox.events.EmployeeCreated;
import me.ioannisioannou.transactional.outbox.events.EmployeeDeleted;
import me.ioannisioannou.transactional.outbox.events.EmployeeUpdated;
import me.ioannisioannou.transactional.outbox.exceptions.EmployeeDoesNotExistException;
import me.ioannisioannou.transactional.outbox.repositories.EmployeeRepository;
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
        eventPublisher.publishEvent(EmployeeCreated.of(employee));
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
                    eventPublisher.publishEvent(EmployeeUpdated.of(updatedEmployee));
                    return updatedEmployee;
                })
                .orElseThrow(() -> new EmployeeDoesNotExistException(id));
    }

    public void delete(UUID id) {
        try {
            employeeRepository.deleteById(id);
            eventPublisher.publishEvent(EmployeeDeleted.of(id));
        } catch (EmptyResultDataAccessException ignored) {
            throw new EmployeeDoesNotExistException(id);
        }
    }
}

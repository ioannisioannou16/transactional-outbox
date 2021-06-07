package me.ioannisioannou.transactional.outbox.producer.controllers;

import lombok.RequiredArgsConstructor;
import me.ioannisioannou.transactional.outbox.producer.entities.Employee;
import me.ioannisioannou.transactional.outbox.producer.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable @NotNull UUID employeeId) {
        return employeeService.get(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@PathVariable @NotNull UUID employeeId, @RequestBody @Valid Employee employee) {
        return employeeService.update(employeeId, employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable @NotNull UUID employeeId) {
        employeeService.delete(employeeId);
    }

}

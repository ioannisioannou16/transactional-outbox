package me.ioannisioannou.transactional.outbox.employee.controllers;

import lombok.RequiredArgsConstructor;
import me.ioannisioannou.transactional.outbox.employee.dtos.CreateEmployeeRequest;
import me.ioannisioannou.transactional.outbox.employee.dtos.CreateEmployeeResponse;
import me.ioannisioannou.transactional.outbox.employee.dtos.UpdateEmployeeRequest;
import me.ioannisioannou.transactional.outbox.employee.dtos.UpdateEmployeeResponse;
import me.ioannisioannou.transactional.outbox.employee.entities.Employee;
import me.ioannisioannou.transactional.outbox.employee.mappers.EmployeeMapper;
import me.ioannisioannou.transactional.outbox.employee.services.EmployeeService;
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
    private final EmployeeMapper employeeMapper;

    @PostMapping
    public CreateEmployeeResponse createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
        return employeeMapper.toCreateEmployeeResponse(
                employeeService.create(employeeMapper.toCreateEmployeeEntity(createEmployeeRequest)));
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
    public UpdateEmployeeResponse updateEmployee(@PathVariable @NotNull UUID employeeId, @RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest) {
        return employeeMapper.toUpdateEmployeeResponse(
                employeeService.update(employeeId, employeeMapper.toUpdateEmployeeEntity(updateEmployeeRequest)));
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable @NotNull UUID employeeId) {
        employeeService.delete(employeeId);
    }

}

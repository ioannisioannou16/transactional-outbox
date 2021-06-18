package me.ioannisioannou.transactional.outbox.employee.repositories;

import me.ioannisioannou.transactional.outbox.employee.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}

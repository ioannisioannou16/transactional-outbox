package me.ioannisioannou.transactional.outbox.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeDoesNotExistException extends RuntimeException {
    public EmployeeDoesNotExistException(UUID id) {
        super(String.format("Employee with %s does not exist", id.toString()));
    }
}

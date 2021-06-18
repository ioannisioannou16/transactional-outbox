package me.ioannisioannou.transactional.outbox.employee.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateEmployeeResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}

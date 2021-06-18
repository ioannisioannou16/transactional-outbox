package me.ioannisioannou.transactional.outbox.employee.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateEmployeeResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
}

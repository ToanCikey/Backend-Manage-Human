package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.common.ContractType;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContractUpdate {

    @Min(value = 1, message = "Id must be at least 1")
    private Long id;

    private ContractType contractType;

    @NotNull(message = "startDate must not be null")
    @Past(message = "startDate must be in the past")
    private LocalDateTime startDate;

    @NotNull(message = "endDate must not be null")
    @Past(message = "endDate must be in the past")
    private LocalDateTime endDate;

    @NotBlank(message = "notes must be not blank")
    @Size(min = 5, message = "notes minimum 5 characters")
    private String notes;
}

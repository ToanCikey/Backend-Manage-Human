package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.common.ContractType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ContractRequest implements Serializable {

    private ContractType contractType;

    @NotNull(message = "StartDate must not be null")
    private LocalDateTime startDate;

    @NotNull(message = "EndDate must not be null")
    private LocalDateTime endDate;

    @NotBlank(message = "Notes must be not blank")
    @Size(min = 5, message = "Notes minimum 5 characters")
    private String notes;

    @NotNull(message = "EmployeeId must be not null")
    @Min(value = 1, message = "EmployeeId must be at least 1")
    private Long employeeId;
}

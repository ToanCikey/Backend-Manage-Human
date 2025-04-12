package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class SalaryUpdateRequest implements Serializable {
    @NotNull(message = "Id must not be null")
    @Min(value = 1, message = "Id must be at least 1")
    private Long id;

    @NotBlank(message = "Name must be not blank")
    @Size(min = 5, message = "Name minimum 5 characters")
    private String name;

    @NotNull(message = "BasicSalary must not be null")
    @DecimalMin(value = "0.0", message = "BasicSalary must be at least 0")
    private Double basicSalary;

    @NotNull(message = "Allowance must not be null")
    @DecimalMin(value = "0.0", message = "Allowance must be at least 0")
    private Double allowance;

    @NotNull(message = "EffectiveDate must not be null")
    private LocalDateTime effectiveDate;

    @Min(value = 1, message = "Employee must be at least 1")
    private Long employeeId;
}

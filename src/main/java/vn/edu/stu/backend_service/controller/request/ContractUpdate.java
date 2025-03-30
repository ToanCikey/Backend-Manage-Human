package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.common.ContractType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ContractUpdate implements Serializable {
    @NotNull(message = "Id must not be null")
    @Min(value = 1, message = "Id must be at least 1")
    private Long id;

    private ContractType contractType;

    @NotNull(message = "StartDate must not be null")
    @Past(message = "StartDate must be in the past")
    private LocalDateTime startDate;

    @NotNull(message = "EndDate must not be null")
    @Past(message = "EndDate must be in the past")
    private LocalDateTime endDate;

    @NotBlank(message = "Notes must be not blank")
    @Size(min = 5, message = "Notes minimum 5 characters")
    private String notes;
}

package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.common.EmployeeStatus;
import vn.edu.stu.backend_service.common.Gender;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeUpdate {
    @Min(value = 1, message = "Id must be at least 1")
    private Long id;

    @NotBlank(message = "fullName must be not blank")
    @Size(min = 5, message = "fullName minimum 5 characters")
    private String fullName;

    @NotNull(message = "dateOfBirth must not be null")
    @Past(message = "dateOfBirth must be in the past")
    private LocalDateTime dateOfBirth;

    @NotNull(message = "gender must not be null")
    private Gender gender;

    @NotBlank(message = "phone must be not blank")
    @Size(min = 10, message = "phone minimum 10 characters")
    private String phone;

    @NotBlank(message = "address must be not blank")
    @Size(min = 5, message = "address minimum 5 characters")
    private String address;

    @NotNull(message = "joiningDate must not be null")
    @Past(message = "joiningDate must be in the past")
    private LocalDateTime joiningDate;

    @NotNull(message = "employeeStatus must not be null")
    private EmployeeStatus employeeStatus;

    @Min(value = 1, message = "userId must be at least 1")
    private Long userId;

    @Min(value = 1, message = "positionId must be at least 1")
    private Long positionId;

    @Min(value = 1, message = "departmentId must be at least 1")
    private Long departmentId;
}

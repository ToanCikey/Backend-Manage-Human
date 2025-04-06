package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.common.EmployeeStatus;
import vn.edu.stu.backend_service.common.Gender;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeRequest implements Serializable {

    @NotBlank(message = "FullName must be not blank")
    @Size(min = 5, message = "FullName minimum 5 characters")
    private String fullName;

    @NotNull(message = "DateOfBirth must not be null")
    @Past(message = "DateOfBirth must be in the past")
    private LocalDateTime dateOfBirth;

    @NotNull(message = "Gender must not be null")
    private Gender gender;

    @NotBlank(message = "Phone must be not blank")
    @Size(min = 10, message = "Phone minimum 10 characters")
    private String phone;

    @NotBlank(message = "Address must be not blank")
    @Size(min = 5, message = "Address minimum 5 characters")
    private String address;

    @NotNull(message = "JoiningDate must not be null")
    @Past(message = "JoiningDate must be in the past")
    private LocalDateTime joiningDate;

    @NotNull(message = "EmployeeStatus must not be null")
    private EmployeeStatus employeeStatus;

    @Min(value = 1, message = "UserId must be at least 1")
    private Long userId;

    @Min(value = 1, message = "PositionId must be at least 1")
    private Long positionId;

}

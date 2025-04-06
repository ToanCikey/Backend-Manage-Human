package vn.edu.stu.backend_service.controller.response.employee;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.common.EmployeeStatus;
import vn.edu.stu.backend_service.common.Gender;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeDetailResponse {
    private Long id;

    private String fullName;

    private String positionName;

    private String departmentName;

    private LocalDateTime dateOfBirth;

    private Gender gender;

    private String phone;

    private String address;

    private LocalDateTime joiningDate;

    private EmployeeStatus employeeStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

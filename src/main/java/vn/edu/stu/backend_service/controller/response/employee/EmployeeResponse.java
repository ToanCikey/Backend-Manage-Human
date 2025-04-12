package vn.edu.stu.backend_service.controller.response.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.stu.backend_service.common.EmployeeStatus;
import vn.edu.stu.backend_service.common.Gender;
import vn.edu.stu.backend_service.model.UserEntity;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;

    private Long positionId;

    private Long departmentId;

    private Long userId;

    private String fullName;

    private LocalDateTime dateOfBirth;

    private Gender gender;

    private String phone;

    private String address;

    private LocalDateTime joiningDate;

    private EmployeeStatus employeeStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}

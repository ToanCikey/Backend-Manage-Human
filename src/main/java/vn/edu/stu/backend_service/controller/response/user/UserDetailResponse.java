package vn.edu.stu.backend_service.controller.response.user;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.common.UserType;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeResponse;


import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserDetailResponse implements Serializable {
    private String username;

    private String email;

    private UserType type;

    private UserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private EmployeeResponse employee;
}

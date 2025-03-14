package vn.edu.stu.backend_service.controller.request;

import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.common.UserType;

import java.io.Serializable;

public class UserUpdateRequest implements Serializable {
    private String username;
    private String password;
    private String email;
    private UserType type;
    private UserStatus status;
}

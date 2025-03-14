package vn.edu.stu.backend_service.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.common.UserType;

import java.io.Serializable;

@Getter
@ToString
public class UserCreationRequest implements Serializable {
    private String username;
    private String password;
    private String email;
    private UserType type;
    private UserStatus status;
}

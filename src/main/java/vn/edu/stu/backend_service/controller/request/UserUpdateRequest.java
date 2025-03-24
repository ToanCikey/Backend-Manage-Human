package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.common.UserType;

import java.io.Serializable;
@Getter
@Setter
public class UserUpdateRequest implements Serializable {
    @Email
    @NotBlank(message = "email must be not blank")
    private String email;

    @NotNull(message = "Usertype must not be null")
    private UserType type;

    @NotNull(message = "Userstatus must not be null")
    private UserStatus status;
}

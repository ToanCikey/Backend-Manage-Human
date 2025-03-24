package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.common.UserType;

import java.io.Serializable;

@Getter
@ToString
public class UserCreationRequest implements Serializable {
    @NotBlank(message = "username must be not blank")
    @Size(min = 5, message = "username minimum 5 characters")
    private String username;

    @NotBlank(message = "password must be not blank")
    @Size(min = 6, message = "password minimum 6 characters")
    private String password;

    @Email
    @NotBlank(message = "email must be not blank")
    private String email;

    @NotNull(message = "Usertype must not be null")
    private UserType type;

    @NotNull(message = "Userstatus must not be null")
    private UserStatus status;
}

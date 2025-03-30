package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.common.UserType;

import java.io.Serializable;

@Getter
@ToString
@Setter
public class UserCreationRequest implements Serializable {
    @NotBlank(message = "Username must be not blank")
    @Size(min = 5, message = "Username minimum 5 characters")
    private String username;

    @NotBlank(message = "Password must be not blank")
    @Size(min = 6, message = "Password minimum 6 characters")
    private String password;

    @Email
    @NotBlank(message = "Email must be not blank")
    private String email;

    @NotNull(message = "Usertype must not be null")
    private UserType type;

    @NotNull(message = "Userstatus must not be null")
    private UserStatus status;
}

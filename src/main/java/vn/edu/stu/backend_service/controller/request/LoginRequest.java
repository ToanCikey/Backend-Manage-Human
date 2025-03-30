package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginRequest implements Serializable {
    @Email
    @NotBlank(message = "Email must be not blank")
    private String email;

    @NotBlank(message = "Password must be not blank")
    @Size(min = 6, message = "Password minimum 6 characters")
    private String password;
}

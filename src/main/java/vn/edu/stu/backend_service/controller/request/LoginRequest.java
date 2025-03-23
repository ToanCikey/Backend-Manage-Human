package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @Email
    @NotBlank(message = "email must be not blank")
    private String email;

    @NotBlank(message = "password must be not blank")
    @Size(min = 6, message = "password minimum 6 characters")
    private String password;
}

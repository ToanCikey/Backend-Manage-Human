package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassWordRequest {
    @Email
    @NotBlank(message = "Email must be not blank")
    private String email;

    @NotBlank(message = "OldPassword must be not blank")
    @Size(min = 6, message = "OldPassword minimum 6 characters")
    private String oldPassword;

    @NotBlank(message = "NewPassword must be not blank")
    @Size(min = 6, message = "NewPassword minimum 6 characters")
    private String newPassword;

    @NotBlank(message = "ConfirmPassword must be not blank")
    @Size(min = 6, message = "ConfirmPassword minimum 6 characters")
    private String confirmPassword;
}

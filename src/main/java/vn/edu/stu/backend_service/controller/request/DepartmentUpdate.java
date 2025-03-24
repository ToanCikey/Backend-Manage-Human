package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentUpdate {
    @Min(value = 1, message = "Id must be at least 1")
    private Long id;

    @NotBlank(message = "name must be not blank")
    @Size(min = 5, message = "name minimum 5 characters")
    private String name;

    @NotBlank(message = "description must be not blank")
    @Size(min = 5, message = "description minimum 5 characters")
    private String description;
}

package vn.edu.stu.backend_service.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PositionRequest implements Serializable {
    @NotBlank(message = "Name must be not blank")
    @Size(min = 5, message = "Name minimum 5 characters")
    private String name;

    @NotBlank(message = "Description must be not blank")
    @Size(min = 5, message = "Description minimum 5 characters")
    private String description;

    @NotNull(message = "DepartmentId must be not null")
    @Min(value = 1, message = "DepartmentId must be at least 1")
    private Long departmentId;
}

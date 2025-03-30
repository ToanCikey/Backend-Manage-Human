package vn.edu.stu.backend_service.controller.response.salary;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class SalaryResponse {
    private Long id;

    private String name;

    private Double basicSalary;

    private Double allowance;

    private LocalDateTime effectiveDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

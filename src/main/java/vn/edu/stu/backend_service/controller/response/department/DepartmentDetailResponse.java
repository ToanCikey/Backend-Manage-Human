package vn.edu.stu.backend_service.controller.response.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDetailResponse {
    private String departmentName;
    private Long positionCount;
    private Long employeeCount;
}

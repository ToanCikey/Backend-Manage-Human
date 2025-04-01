package vn.edu.stu.backend_service.controller.response.department;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeResponse;

import java.util.List;
@Getter
@Setter
public class EmployeeByDepartmentResponse {
    List<EmployeeResponse> employees;
}

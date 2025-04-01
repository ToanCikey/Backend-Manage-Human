package vn.edu.stu.backend_service.controller.response.employee;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.controller.response.salary.SalaryResponse;
import java.util.List;

@Getter
@Setter
public class SalaryByEmployeeResponse {
    private List<SalaryResponse> salaries;
}

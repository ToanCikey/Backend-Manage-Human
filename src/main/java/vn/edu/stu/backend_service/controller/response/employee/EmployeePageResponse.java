package vn.edu.stu.backend_service.controller.response.employee;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.controller.response.PageResponseAbstract;
import java.util.List;

@Getter
@Setter
public class EmployeePageResponse extends PageResponseAbstract {
    List<EmployeeResponse> employees;
}

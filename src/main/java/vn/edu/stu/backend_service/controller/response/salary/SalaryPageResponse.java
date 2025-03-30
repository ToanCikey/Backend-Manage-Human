package vn.edu.stu.backend_service.controller.response.salary;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.controller.response.PageResponseAbstract;

import java.util.List;

@Getter
@Setter
public class SalaryPageResponse extends PageResponseAbstract {
    List<SalaryResponse> salaries;
}

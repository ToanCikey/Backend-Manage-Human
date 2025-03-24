package vn.edu.stu.backend_service.controller.response.department;

import lombok.Getter;
import lombok.Setter;
import vn.edu.stu.backend_service.controller.response.PageResponseAbstract;

import java.util.List;

@Getter
@Setter
public class DepartmentPageResponse extends PageResponseAbstract {
    List<DepartmentResponse> departments;
}

package vn.edu.stu.backend_service.service;

import vn.edu.stu.backend_service.controller.request.EmployeeRequest;
import vn.edu.stu.backend_service.controller.request.EmployeeUpdate;
import vn.edu.stu.backend_service.controller.response.employee.EmployeePageResponse;
import vn.edu.stu.backend_service.model.EmployeeEntity;


public interface EmployeeService {
    EmployeeEntity addEmployee(EmployeeRequest employee);
    void updateEmployee(EmployeeUpdate employee);
    void deleteEmployee(Long id);
    EmployeeEntity getEmployeeById(Long id);
    EmployeePageResponse getAllEmployee(String keyword, String sort, int page, int size);
}

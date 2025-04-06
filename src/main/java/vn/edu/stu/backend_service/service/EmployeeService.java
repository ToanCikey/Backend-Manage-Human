package vn.edu.stu.backend_service.service;

import vn.edu.stu.backend_service.controller.request.EmployeeRequest;
import vn.edu.stu.backend_service.controller.request.EmployeeUpdate;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeDetailResponse;
import vn.edu.stu.backend_service.controller.response.employee.EmployeePageResponse;
import vn.edu.stu.backend_service.model.ContractEntity;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.SalaryEntity;

import java.util.List;


public interface EmployeeService {
    EmployeeEntity addEmployee(EmployeeRequest employee);
    void updateEmployee(EmployeeUpdate employee);
    void deleteEmployee(Long id);
    EmployeeEntity getEmployeeById(Long id);
    EmployeePageResponse getAllEmployee(String keyword, String sort, int page, int size);
    List<EmployeeEntity> getAllEmployee();
    List<SalaryEntity> getAllSalaryByEmployeeId(Long id);
    List<ContractEntity> getAllContractByEmployeeId(Long id);
    EmployeeEntity getEmployeeDetail(Long id);
}

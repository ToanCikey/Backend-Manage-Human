package vn.edu.stu.backend_service.service;

import vn.edu.stu.backend_service.controller.request.DepartmentRequest;
import vn.edu.stu.backend_service.controller.request.DepartmentUpdate;
import vn.edu.stu.backend_service.controller.response.department.DepartmentPageResponse;
import vn.edu.stu.backend_service.model.DepartmentEntity;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.PositionEntity;

import java.util.List;

public interface DepartmentService {
    DepartmentEntity addDepartment(DepartmentRequest department);
    void updateDepartment(DepartmentUpdate department);
    void deleteDepartment(Long id);
    DepartmentEntity getDepartmentById(Long id);
    DepartmentEntity getDepartmentByName(String name);
    DepartmentPageResponse getAllDepartments(String keyword, String sort, int page, int size);
    List<DepartmentEntity> getAllDepartments();
    List<PositionEntity> getAllPositionByDepartmentId(Long id);
    List<EmployeeEntity> getEmployeeByDepartmentId(Long id);
}

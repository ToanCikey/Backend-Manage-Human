package vn.edu.stu.backend_service.service;

import vn.edu.stu.backend_service.controller.request.SalaryRequest;
import vn.edu.stu.backend_service.controller.request.SalaryUpdateRequest;
import vn.edu.stu.backend_service.controller.response.salary.SalaryPageResponse;
import vn.edu.stu.backend_service.model.SalaryEntity;

public interface SalaryService {
    SalaryEntity addSalary(SalaryRequest request);
    void updateSalary(SalaryUpdateRequest request);
    void deleteSalary(Long id);
    SalaryPageResponse getAllSalaries(String keyword, String sort, int page, int size);
    SalaryEntity getSalaryById(Long id);
}

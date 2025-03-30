package vn.edu.stu.backend_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.stu.backend_service.controller.request.SalaryRequest;
import vn.edu.stu.backend_service.controller.request.SalaryUpdateRequest;
import vn.edu.stu.backend_service.controller.response.salary.SalaryPageResponse;
import vn.edu.stu.backend_service.controller.response.salary.SalaryResponse;
import vn.edu.stu.backend_service.exception.ResourceNotFoundException;
import vn.edu.stu.backend_service.mapper.SalaryMapper;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.SalaryEntity;
import vn.edu.stu.backend_service.repository.SalaryRepository;
import vn.edu.stu.backend_service.service.EmployeeService;
import vn.edu.stu.backend_service.service.SalaryService;
import vn.edu.stu.backend_service.specification.SalarySpecification;

import java.util.List;

@Slf4j(topic = "SALARY-SERVICE")
@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryRepository salaryRepository;
    private final EmployeeService employeeService;
    private final SalaryMapper salaryMapper;

    @Override
    public SalaryEntity addSalary(SalaryRequest request) {
        log.info("Begin add salary {}", request);
        EmployeeEntity employee = employeeService.getEmployeeById(request.getEmployeeId());
        if (employee != null) {
            SalaryEntity salaryEntity = new SalaryEntity();
            salaryEntity.setName(request.getName());
            salaryEntity.setBasicSalary(request.getBasicSalary());
            salaryEntity.setAllowance(request.getAllowance());
            salaryEntity.setEffectiveDate(request.getEffectiveDate());
            salaryEntity.setEmployee(employee);

            salaryRepository.save(salaryEntity);

            log.info("Saving add salary {}", request);
            return salaryEntity;
        }
        return null;
    }

    @Override
    public void updateSalary(SalaryUpdateRequest request) {
        log.info("Begin update salary {}", request);

        SalaryEntity salary = getSalaryById(request.getId());
        salary.setName(request.getName());
        salary.setBasicSalary(request.getBasicSalary());
        salary.setAllowance(request.getAllowance());
        salary.setEffectiveDate(request.getEffectiveDate());

        if(request.getEmployeeId() != null) {
            EmployeeEntity employee = employeeService.getEmployeeById(request.getEmployeeId());
            salary.setEmployee(employee);
        }
        salaryRepository.save(salary);

        log.info("Saving update salary {}", request);
    }

    @Override
    public void deleteSalary(Long id) {
        log.info("Begin delete salary {}", id);

        SalaryEntity salary = getSalaryById(id);
        if(salary != null) {
            salaryRepository.delete(salary);

            log.info("Deleting salary {}", id);
        }
    }

    @Override
    public SalaryPageResponse getAllSalaries(String keyword, String sort, int page, int size) {
        Specification<SalaryEntity> specification = SalarySpecification.hasKeyword(keyword);

        Sort.Direction direction = ("desc".equalsIgnoreCase(sort)) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortBy = Sort.by(direction, "id");

        Pageable pageable = PageRequest.of(page, size, sortBy);

        Page<SalaryEntity> salaries = salaryRepository.findAll(specification, pageable);

        List<SalaryResponse> salaryResponses = salaryMapper.toMapSalaryList(salaries.getContent());

        SalaryPageResponse salaryPageResponse = new SalaryPageResponse();
        salaryPageResponse.setSalaries(salaryResponses);
        salaryPageResponse.setPageNumber(salaries.getNumber());
        salaryPageResponse.setPageSize(salaries.getSize());
        salaryPageResponse.setTotalPages(salaries.getTotalPages());
        salaryPageResponse.setTotalElements(salaries.getTotalElements());

        return salaryPageResponse;
    }

    @Override
    public SalaryEntity getSalaryById(Long id) {
        return salaryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Salary with id " + id + " not found"));
    }
}

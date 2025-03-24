package vn.edu.stu.backend_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.stu.backend_service.controller.request.EmployeeRequest;
import vn.edu.stu.backend_service.controller.request.EmployeeUpdate;
import vn.edu.stu.backend_service.controller.response.employee.EmployeePageResponse;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeResponse;
import vn.edu.stu.backend_service.exception.ResourceNotFoundException;
import vn.edu.stu.backend_service.mapper.EmployeeMapper;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.UserEntity;
import vn.edu.stu.backend_service.repository.EmployeeRepository;
import vn.edu.stu.backend_service.service.EmployeeService;
import vn.edu.stu.backend_service.service.UserService;
import vn.edu.stu.backend_service.specification.EmployeeSpecification;

import java.util.List;

@Slf4j(topic = "EMPLOYEE-SERVICE")
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserService userService;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeEntity addEmployee(EmployeeRequest employee) {
        log.info("Begin Saving employee {}", employee);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFullName(employee.getFullName());
        employeeEntity.setDateOfBirth(employee.getDateOfBirth());
        employeeEntity.setGender(employee.getGender());
        employeeEntity.setPhone(employee.getPhone());
        employeeEntity.setAddress(employee.getAddress());
        employeeEntity.setJoiningDate(employee.getJoiningDate());
        employeeEntity.setEmployeeStatus(employee.getEmployeeStatus());

        if(employee.getUserId() != null) {
            UserEntity user = userService.getUserById(employee.getUserId());
            employeeEntity.setUser(user);
        }

        employeeRepository.save(employeeEntity);
        log.info("Saving employee {}", employee);

        return employeeEntity;
    }

    @Override
    public void updateEmployee(EmployeeUpdate employee) {
        log.info("Begin Update employee {}", employee);
        EmployeeEntity employeeEntity = getEmployeeById(employee.getId());
        if(employeeEntity != null) {
            employeeEntity.setFullName(employee.getFullName());
            employeeEntity.setDateOfBirth(employee.getDateOfBirth());
            employeeEntity.setGender(employee.getGender());
            employeeEntity.setPhone(employee.getPhone());
            employeeEntity.setAddress(employee.getAddress());
            employeeEntity.setJoiningDate(employee.getJoiningDate());
            employeeEntity.setEmployeeStatus(employee.getEmployeeStatus());

            if(employee.getUserId() != null) {
                UserEntity user = userService.getUserById(employee.getUserId());
                employeeEntity.setUser(user);
            }

            employeeRepository.save(employeeEntity);
            log.info("Updating employee {}", employee);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = getEmployeeById(id);
        if(employeeEntity != null) {
            employeeRepository.delete(employeeEntity);
        }
    }

    @Override
    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public EmployeePageResponse getAllEmployee(String keyword, String sort, int page, int size) {
        Specification<EmployeeEntity> specification = EmployeeSpecification.hasKeyword(keyword);

        Sort.Direction direction = ("desc".equalsIgnoreCase(sort)) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortBy = Sort.by(direction, "id");

        Pageable pageable = PageRequest.of(page, size, sortBy);

        Page<EmployeeEntity> employees = employeeRepository.findAll(specification, pageable);

        List<EmployeeResponse> employeeResponses = employeeMapper.toMapListEmployee(employees.getContent());

        EmployeePageResponse employeePageResponse = new EmployeePageResponse();
        employeePageResponse.setEmployees(employeeResponses);
        employeePageResponse.setPageNumber(employees.getNumber());
        employeePageResponse.setPageSize(employees.getSize());
        employeePageResponse.setTotalElements(employees.getTotalElements());
        employeePageResponse.setTotalPages(employees.getTotalPages());

        return employeePageResponse;
    }
}

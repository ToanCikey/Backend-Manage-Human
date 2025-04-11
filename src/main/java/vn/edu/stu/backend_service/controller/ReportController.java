package vn.edu.stu.backend_service.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.stu.backend_service.controller.response.ResponseSuccess;
import vn.edu.stu.backend_service.service.*;

@RestController
@RequestMapping("/api/v1/report")
@Tag(name = "Report Controller")
@Slf4j(topic = "REPORT-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class ReportController {
    private final UserService userService;
    private final ContractService contractService;
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final SalaryService salaryService;
    private final PositionService positionService;

    @GetMapping("/users/count")
    public ResponseSuccess<?> getCountUsers() {
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get count of users", userService.totalUsers());
    }

    @GetMapping("/contracts/count")
    public ResponseSuccess<?> getCountContracts() {
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get count of contracts", contractService.totalContracts());
    }

    @GetMapping("/departments/count")
    public ResponseSuccess<?> getCountDepartments() {
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get count of departments", departmentService.totalDepartments());
    }

    @GetMapping("/employees/count")
    public ResponseSuccess<?> getCountEmployees() {
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get count of employees", employeeService.totalEmployee());
    }

    @GetMapping("/positions/count")
    public ResponseSuccess<?> getCountPositions() {
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get count of positions", positionService.totalPositions());
    }

    @GetMapping("/salaries/count")
    public ResponseSuccess<?> getCountSalaries() {
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get count of salaries", salaryService.totalSalaries());
    }

    @GetMapping("/departments/detail")
    public ResponseSuccess<?> getDepartmentDetails() {
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get all departments with how many employees and positions", departmentService.getDepartmentDetail());
    }

    @GetMapping("/positions/detail")
    public ResponseSuccess<?> getPositionDetails() {
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Get all positions with how many employee", positionService.getAllPositionDetails());
    }

}

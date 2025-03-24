package vn.edu.stu.backend_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.backend_service.controller.request.EmployeeRequest;
import vn.edu.stu.backend_service.controller.request.EmployeeUpdate;
import vn.edu.stu.backend_service.controller.response.ResponseSuccess;
import vn.edu.stu.backend_service.controller.response.employee.EmployeePageResponse;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeResponse;
import vn.edu.stu.backend_service.controller.response.user.UserPageResponse;
import vn.edu.stu.backend_service.mapper.EmployeeMapper;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
@Tag(name = "Employee Controller")
@Slf4j(topic = "EMPLOYEE-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Operation(summary = "Create Employee", description = "API add new employee to database")
    @PostMapping("/add")
    public ResponseSuccess<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        log.info("Create employee: {}", request);
        EmployeeEntity employee = employeeService.addEmployee(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create Employee successful", employeeMapper.toMapEmployee(employee));
    }

    @Operation(summary = "Update Employee", description = "API update employee to database")
    @PutMapping("/update")
    public ResponseSuccess<?> updateEmployee(@Valid @RequestBody EmployeeUpdate request) {
        log.info("Update employee: {}", request);
        employeeService.updateEmployee(request);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Update Employee successful");
    }

    @Operation(summary = "Delete Employee", description = "API delete employee to database")
    @DeleteMapping("/delete/{id}")
    public ResponseSuccess<?> deleteEmployee(@Min(1) @PathVariable Long id) {
        log.info("Delete employee: {}", id);
        employeeService.deleteEmployee(id);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Delete Employee successful");
    }

    @Operation(summary = "GetAll employee by search, sort, page", description = "API get all employee to database")
    @GetMapping("/listbypage")
    public ResponseSuccess<EmployeePageResponse> getAllEmployeeByPageAndSort(@RequestParam(required = false) String keyword,
                                                                      @RequestParam(required = false) String sort,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        log.info("GetAll Employee by page and sort: {}");
        EmployeePageResponse employeePageResponse = employeeService.getAllEmployee(keyword, sort, page, size);
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all employee by page and sort successful", employeePageResponse);
    }
}

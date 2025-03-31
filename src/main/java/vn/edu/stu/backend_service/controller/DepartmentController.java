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
import vn.edu.stu.backend_service.controller.request.DepartmentRequest;
import vn.edu.stu.backend_service.controller.request.DepartmentUpdate;
import vn.edu.stu.backend_service.controller.response.ResponseSuccess;
import vn.edu.stu.backend_service.controller.response.department.DepartmentPageResponse;
import vn.edu.stu.backend_service.controller.response.department.DepartmentResponse;
import vn.edu.stu.backend_service.mapper.DepartmentMapper;
import vn.edu.stu.backend_service.model.DepartmentEntity;
import vn.edu.stu.backend_service.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@Tag(name = "Department Controller")
@Slf4j(topic = "DEPARTMENT-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Operation(summary = "Create Department", description = "API add new department to database")
    @PostMapping("/add")
    public ResponseSuccess<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest request) {
        log.info("Create Department: {}", request);
        DepartmentEntity department = departmentService.addDepartment(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create Department successful", departmentMapper.toMapDepartment(department));
    }

    @Operation(summary = "Delete Department", description = "API delete department to database")
    @DeleteMapping("/delete/{id}")
    public ResponseSuccess<?> deleteDepartment(@Min(1) @PathVariable Long id) {
        log.info("Delete Department: {}", id);
        departmentService.deleteDepartment(id);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Delete Department successful");
    }

    @Operation(summary = "Update Department", description = "API update department to database")
    @PutMapping("/update")
    public ResponseSuccess<?> updateDepartment(@Valid @RequestBody DepartmentUpdate request) {
        log.info("Update Department: {}", request);
        departmentService.updateDepartment(request);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Update Department successful");
    }

    @Operation(summary = "GetAll department by search, sort, page", description = "API get all departments to database")
    @GetMapping("/listbypage")
    public ResponseSuccess<DepartmentPageResponse> getAllDepartmentsByPageAndSort(@RequestParam(required = false) String keyword,
                                                                            @RequestParam(required = false) String sort,
                                                                            @RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "10") int size) {
        log.info("GetAll User by department and sort: {}");

        DepartmentPageResponse departmentPageResponse = departmentService.getAllDepartments(keyword, sort, page, size);

        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all department by page and sort successful", departmentPageResponse);
    }

    @Operation(summary = "GetAll department", description = "API get all departments to database")
    @GetMapping("/list")
    public ResponseSuccess<?> getAllDepartments() {
        log.info("GetAll User by department : {}");

        List<DepartmentEntity> departmentEntities = departmentService.getAllDepartments();
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all department successful", departmentMapper.toMapListDepartment(departmentEntities));
    }

}

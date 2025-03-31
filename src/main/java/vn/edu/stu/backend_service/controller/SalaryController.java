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
import vn.edu.stu.backend_service.controller.request.SalaryRequest;
import vn.edu.stu.backend_service.controller.request.SalaryUpdateRequest;
import vn.edu.stu.backend_service.controller.response.ResponseSuccess;
import vn.edu.stu.backend_service.controller.response.salary.SalaryPageResponse;
import vn.edu.stu.backend_service.controller.response.salary.SalaryResponse;
import vn.edu.stu.backend_service.mapper.SalaryMapper;
import vn.edu.stu.backend_service.model.SalaryEntity;
import vn.edu.stu.backend_service.service.SalaryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/salary")
@Tag(name = "Salary Controller")
@Slf4j(topic = "SALARY-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class SalaryController {
    private final SalaryService salaryService;
    private final SalaryMapper salaryMapper;

    @Operation(summary = "Create Salary", description = "API add new salary to database")
    @PostMapping("/add")
    public ResponseSuccess<SalaryResponse> createSalary(@Valid @RequestBody SalaryRequest request) {
        log.info("Create salary: {}", request);
        SalaryEntity salary = salaryService.addSalary(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create salary successful", salaryMapper.toMapSlaResponse(salary));
    }

    @Operation(summary = "Delete Salary", description = "API delete salary to database")
    @DeleteMapping("/delete/{id}")
    public ResponseSuccess<?> deleteSalary(@Min(1) @PathVariable Long id) {
        log.info("Delete Salary: {}", id);
        salaryService.deleteSalary(id);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Delete salary successful");
    }

    @Operation(summary = "Update Salary", description = "API update salary to database")
    @PutMapping("/update")
    public ResponseSuccess<?> updateSalary(@Valid @RequestBody SalaryUpdateRequest request) {
        log.info("Update Salary: {}", request);
        salaryService.updateSalary(request);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Update Salary successful");
    }

    @Operation(summary = "GetAll salary by search, sort, page", description = "API get all salaries to database")
    @GetMapping("/listbypage")
    public ResponseSuccess<SalaryPageResponse> getAllSalaryByPageAndSort(@RequestParam(required = false) String keyword,
                                                                           @RequestParam(required = false) String sort,
                                                                           @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size) {
        log.info("GetAll salaries by page and sort: {}");
        SalaryPageResponse salaryPageResponse = salaryService.getAllSalaries(keyword, sort, page, size);
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all salaries by page and sort successful", salaryPageResponse);
    }

    @Operation(summary = "GetAll salary ", description = "API get all salaries to database")
    @GetMapping("/list")
    public ResponseSuccess<?> getAllSalary() {
        log.info("GetAll salaries: {}");

        List<SalaryEntity> salaries = salaryService.getAllSalaries();
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all salaries successful", salaryMapper.toMapSalaryList(salaries));
    }
}

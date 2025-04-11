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
import vn.edu.stu.backend_service.controller.request.PositionRequest;
import vn.edu.stu.backend_service.controller.request.PositionUpdateRequest;
import vn.edu.stu.backend_service.controller.response.position.PositionPageRespone;
import vn.edu.stu.backend_service.controller.response.position.PositionResponse;
import vn.edu.stu.backend_service.controller.response.ResponseSuccess;
import vn.edu.stu.backend_service.mapper.PositionMapper;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.PositionEntity;
import vn.edu.stu.backend_service.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/position")
@Tag(name = "Position Controller")
@Slf4j(topic = "POSITION-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class PositionController {
    private final PositionService positionService;
    private final PositionMapper positionMapper;

    @Operation(summary = "Create Position", description = "API add new position to database")
    @PostMapping("/add")
    public ResponseSuccess<PositionResponse> createPosition(@Valid @RequestBody PositionRequest request) {
        log.info("Create position: {}", request);
        PositionEntity position = positionService.savePosition(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create position successful", positionMapper.toPositionRespone(position));
    }

    @Operation(summary = "Delete Position", description = "API delete position to database")
    @DeleteMapping("/delete/{id}")
    public ResponseSuccess<?> deletePosition(@Min(1) @PathVariable Long id) {
        log.info("Delete position: {}", id);
        positionService.deletePosition(id);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Delete position successful");
    }

    @Operation(summary = "Update Position", description = "API update position to database")
    @PutMapping("/update")
    public ResponseSuccess<?> updatePosition(@Valid @RequestBody PositionUpdateRequest request) {
        log.info("Update Position: {}", request);
        positionService.updatePosition(request);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Update Position successful");
    }

    @Operation(summary = "GetAll position by search, sort, page", description = "API get all positions to database")
    @GetMapping("/listbypage")
    public ResponseSuccess<PositionPageRespone> getAllPositionByPageAndSort(@RequestParam(required = false) String keyword,
                                                                         @RequestParam(required = false) String sort,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size) {
        log.info("GetAll position by page and sort: {}");
        PositionPageRespone positionPageRespone = positionService.getAllPositions(keyword, sort, page, size);
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all position by page and sort successful", positionPageRespone);
    }

    @Operation(summary = "GetAll position", description = "API get all positions to database")
    @GetMapping("/list")
    public ResponseSuccess<?> getAllPosition() {
        log.info("GetAll position: {}");

        List<PositionEntity> positionEntities = positionService.getAllPositions();
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all position successful", positionMapper.toPositionResponesList(positionEntities));
    }

    @Operation(summary = "Get all employee by position id", description = "API get all employee by position id to database")
    @GetMapping("/position-employee/{id}")
    public ResponseSuccess<?> getAllEmployeeByPosition(@Min(1) @PathVariable Long id) {
        log.info("Get all employee by position id: {}");

        List<EmployeeEntity> entities = positionService.getEmployeeByPositionId(id);
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all employee by position id successful", positionMapper.toEmployeeByPositionId(entities));
    }

    @Operation(summary = "Get position by id", description = "API get position by id to database")
    @GetMapping("/{id}")
    public ResponseSuccess<?> getPositionById(@Min(1) @PathVariable Long id) {
        log.info("Get position by id: {}");

        PositionEntity position = positionService.getPositionById(id);
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get position by id successful", positionMapper.toPositionRespone(position));
    }
}

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
import vn.edu.stu.backend_service.controller.request.ContractRequest;
import vn.edu.stu.backend_service.controller.request.ContractUpdate;
import vn.edu.stu.backend_service.controller.response.ResponseSuccess;
import vn.edu.stu.backend_service.controller.response.contract.ContractPageResponse;
import vn.edu.stu.backend_service.controller.response.contract.ContractResponse;
import vn.edu.stu.backend_service.mapper.ContractMapper;
import vn.edu.stu.backend_service.model.ContractEntity;
import vn.edu.stu.backend_service.service.ContractService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contract")
@Tag(name = "Contract Controller")
@Slf4j(topic = "CONTRACT-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class ContractController {
    private final ContractService contractService;
    private final ContractMapper contractMapper;

    @Operation(summary = "Create Contract", description = "API add new contract to database")
    @PostMapping("/add")
    public ResponseSuccess<ContractResponse> createContract(@Valid @RequestBody ContractRequest request) {
        log.info("Create Contract: {}", request);
        ContractEntity contract = contractService.addContract(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create Contract successful", contractMapper.toMapContract(contract));
    }

    @Operation(summary = "Delete Contract", description = "API delete contract to database")
    @DeleteMapping("/delete/{id}")
    public ResponseSuccess<?> deleteContract(@Min(1) @PathVariable Long id) {
        log.info("Delete Contract: {}", id);
        contractService.deleteContract(id);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Delete Contract successful");
    }

    @Operation(summary = "Update Contract", description = "API update contract to database")
    @PutMapping("/update")
    public ResponseSuccess<?> updateContract(@Valid @RequestBody ContractUpdate request) {
        log.info("Update Contract: {}", request);
        contractService.updateContract(request);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Update Contract successful");
    }

    @Operation(summary = "GetAll contract by search, sort, page", description = "API get all contracts to database")
    @GetMapping("/listbypage")
    public ResponseSuccess<ContractPageResponse> getAllContractsByPageAndSort(@RequestParam(required = false) String keyword,
                                                                                @RequestParam(required = false) String sort,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size) {
        log.info("GetAll Contract by page and sort: {}");

        ContractPageResponse contractPageResponse = contractService.getAllContracts(keyword, sort, page, size);

        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all contracts by page and sort successful", contractPageResponse);
    }

    @Operation(summary = "Get all contracts", description = "API get all contracts to database")
    @GetMapping("/list")
    public ResponseSuccess<List<ContractResponse>> getAllContracts() {
        log.info("GetAll Contract : {}");

        List<ContractEntity> contractEntities = contractService.getAllContracts();
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all contracts successful", contractMapper.toMapListContract(contractEntities));
    }

    @Operation(summary = "Get contract by id", description = "API contract by id to database")
    @GetMapping("/{id}")
    public ResponseSuccess<?> getContractById(@Min(1) @PathVariable Long id) {
        log.info("Get contract by id : {}");

        ContractEntity contractEntity = contractService.getContractById(id);
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get contract by id successful", contractMapper.toMapContract(contractEntity));
    }


}

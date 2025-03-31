package vn.edu.stu.backend_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.edu.stu.backend_service.controller.request.ContractRequest;
import vn.edu.stu.backend_service.controller.request.ContractUpdate;
import vn.edu.stu.backend_service.controller.response.contract.ContractPageResponse;
import vn.edu.stu.backend_service.controller.response.contract.ContractResponse;
import vn.edu.stu.backend_service.exception.ResourceNotFoundException;
import vn.edu.stu.backend_service.mapper.ContractMapper;
import vn.edu.stu.backend_service.model.ContractEntity;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.repository.ContractRepository;
import vn.edu.stu.backend_service.service.ContractService;
import vn.edu.stu.backend_service.service.EmployeeService;
import vn.edu.stu.backend_service.specification.ContractSpecification;

import java.util.List;


@Slf4j(topic = "CONTRACT-SERVICE")
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;
    private final EmployeeService employeeService;

    @Override
    public ContractEntity addContract(ContractRequest contract) {
        log.info("Begin Saving contract {}", contract);

        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setContractType(contract.getContractType());
        contractEntity.setStartDate(contract.getStartDate());
        contractEntity.setEndDate(contract.getEndDate());
        contractEntity.setNotes(contract.getNotes());

        if(contract.getEmployeeId() != null) {
            EmployeeEntity employee = employeeService.getEmployeeById(contract.getEmployeeId());
            contractEntity.setEmployee(employee);
        }
        contractRepository.save(contractEntity);
        log.info("Saving contract {}", contract);

        return contractEntity;
    }

    @Override
    public void updateContract(ContractUpdate contract) {
        log.info("Begin Updating contract {}", contract);

        ContractEntity contractEntity = getContractById(contract.getId());
        if(contractEntity != null) {
            contractEntity.setContractType(contract.getContractType());
            contractEntity.setStartDate(contract.getStartDate());
            contractEntity.setEndDate(contract.getEndDate());
            contractEntity.setNotes(contract.getNotes());

            if(contract.getEmployeeId() != null){
                EmployeeEntity employee = employeeService.getEmployeeById(contract.getEmployeeId());
                contractEntity.setEmployee(employee);
            }
            contractRepository.save(contractEntity);
        }
        log.info("Updating contract {}", contract);
    }

    @Override
    public void deleteContract(Long id) {
        log.info("Begin Deleting contract {}", id);
        ContractEntity contractEntity = getContractById(id);
        if(contractEntity != null) {
            contractRepository.delete(contractEntity);
        }
        log.info("Deleting contract {}", id);
    }

    @Override
    public ContractPageResponse getAllContracts(String keyword, String sort, int page, int size) {
        log.info("Begin get all contract {}", page + "," + size);

        Specification<ContractEntity> specification = ContractSpecification.hasKeyword(keyword);

        Sort.Direction direction = ("desc".equalsIgnoreCase(sort)) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortBy = Sort.by(direction, "id");

        Pageable pageable = PageRequest.of(page, size, sortBy);

        Page<ContractEntity> contracts = contractRepository.findAll(specification, pageable);

        List<ContractResponse> contractResponses = contractMapper.toMapListContract(contracts.getContent());

        ContractPageResponse contractPageResponse = new ContractPageResponse();
        contractPageResponse.setContracts(contractResponses);
        contractPageResponse.setPageNumber(contracts.getNumber());
        contractPageResponse.setPageSize(contracts.getSize());
        contractPageResponse.setTotalPages(contracts.getTotalPages());
        contractPageResponse.setTotalElements(contracts.getTotalElements());

        log.info("Get all contract {}", page + "," + size);
        return contractPageResponse;
    }

    @Override
    public ContractEntity getContractById(Long id) {
        return contractRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contract with " + id + " not found"));
    }

    @Override
    public List<ContractEntity> getAllContracts() {
        return contractRepository.findAll();
    }
}

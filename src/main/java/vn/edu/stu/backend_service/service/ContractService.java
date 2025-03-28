package vn.edu.stu.backend_service.service;

import vn.edu.stu.backend_service.controller.request.ContractRequest;
import vn.edu.stu.backend_service.controller.request.ContractUpdate;
import vn.edu.stu.backend_service.controller.response.contract.ContractPageResponse;
import vn.edu.stu.backend_service.model.ContractEntity;

public interface ContractService {
    ContractEntity addContract(ContractRequest contract);
    void updateContract(ContractUpdate contract);
    void deleteContract(Long id);
    ContractPageResponse getAllContracts(String keyword, String sort, int page, int size);
    ContractEntity getContractById(Long id);
}

package vn.edu.stu.backend_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.backend_service.controller.response.contract.ContractResponse;
import vn.edu.stu.backend_service.model.ContractEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContractMapper {
    private final ModelMapper modelMapper;

    public ContractResponse toMapContract(ContractEntity contractEntity) {
        return modelMapper.map(contractEntity, ContractResponse.class);
    }

    public List<ContractResponse> toMapListContract(List<ContractEntity> contractEntityList) {
        return contractEntityList.stream().map((contract)->
                modelMapper.map(contract, ContractResponse.class)
                ).toList();
    }
}

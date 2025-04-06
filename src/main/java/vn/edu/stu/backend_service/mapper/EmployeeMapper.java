package vn.edu.stu.backend_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.backend_service.controller.response.contract.ContractResponse;
import vn.edu.stu.backend_service.controller.response.employee.ContractByEmployeeResponse;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeDetailResponse;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeResponse;
import vn.edu.stu.backend_service.controller.response.employee.SalaryByEmployeeResponse;
import vn.edu.stu.backend_service.controller.response.salary.SalaryResponse;
import vn.edu.stu.backend_service.controller.response.user.UserDetailResponse;
import vn.edu.stu.backend_service.model.ContractEntity;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.SalaryEntity;
import vn.edu.stu.backend_service.model.UserEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    private final ModelMapper modelMapper;
    private final PositionMapper positionMapper;
    private final DepartmentMapper departmentMapper;

    public EmployeeResponse toMapEmployee(EmployeeEntity employeeEntity) {
        return modelMapper.map(employeeEntity, EmployeeResponse.class);
    }

    public List<EmployeeResponse> toMapListEmployee(List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream().map((employee)->
                modelMapper.map(employee, EmployeeResponse.class))
                .toList();
    }

    public SalaryByEmployeeResponse toMapSalaryByEmployee(List<SalaryEntity> salaryEntities) {
        SalaryByEmployeeResponse response = new SalaryByEmployeeResponse();
        response.setSalaries(salaryEntities.stream().map((salaryEntity)->
                modelMapper.map(salaryEntity, SalaryResponse.class))
                .toList());
        return response;
    }

    public ContractByEmployeeResponse toMapContractByEmployee(List<ContractEntity> contractEntities) {
        ContractByEmployeeResponse response = new ContractByEmployeeResponse();
        response.setContracts(contractEntities.stream()
                .map(contract -> modelMapper.map(contract, ContractResponse.class))
                .toList());
        return response;
    }

    public EmployeeDetailResponse toMapEmployeeDetailResponse(EmployeeEntity employee) {
        EmployeeDetailResponse response = new EmployeeDetailResponse();
        modelMapper.map(employee, response);

        if(employee.getPosition() != null){
            response.setPosition(positionMapper.toPositionRespone(employee.getPosition()));
        }

        if(employee.getPosition().getDepartment() != null){
           response.setDepartment(departmentMapper.toMapDepartment(employee.getPosition().getDepartment()));
        }
        return response;
    }

}

package vn.edu.stu.backend_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeResponse;
import vn.edu.stu.backend_service.model.EmployeeEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmployeeMapper {
    private final ModelMapper modelMapper;

    public EmployeeResponse toMapEmployee(EmployeeEntity employeeEntity) {
        return modelMapper.map(employeeEntity, EmployeeResponse.class);
    }

    public List<EmployeeResponse> toMapListEmployee(List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream().map((employee)->
                modelMapper.map(employee, EmployeeResponse.class))
                .toList();
    }
}

package vn.edu.stu.backend_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeResponse;
import vn.edu.stu.backend_service.controller.response.position.EmployeeByPositionResponse;
import vn.edu.stu.backend_service.controller.response.position.PositionResponse;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.PositionEntity;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PositionMapper {
    private final ModelMapper modelMapper;

    public PositionResponse toPositionRespone(PositionEntity position) {
        PositionResponse positionResponse = new PositionResponse();
        modelMapper.map(position, positionResponse);
        if(position.getDepartment() != null){
            positionResponse.setDepartmentId(position.getDepartment().getId());
        }
        return positionResponse;
    }

    public List<PositionResponse> toPositionResponesList(List<PositionEntity> positionEntities) {
        return positionEntities.stream().map((position)->
                modelMapper.map(position, PositionResponse.class))
                .toList();
    }

    public EmployeeByPositionResponse toEmployeeByPositionId(List<EmployeeEntity> entities) {
        EmployeeByPositionResponse response = new EmployeeByPositionResponse();
        response.setEmployees(entities.stream().map((employee)->
                modelMapper.map(employee, EmployeeResponse.class)).toList());
        return response;
    }
}

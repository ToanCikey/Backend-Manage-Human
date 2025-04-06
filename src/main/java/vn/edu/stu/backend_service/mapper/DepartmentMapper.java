package vn.edu.stu.backend_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.backend_service.controller.response.department.DepartmentResponse;
import vn.edu.stu.backend_service.controller.response.department.EmployeeByDepartmentResponse;
import vn.edu.stu.backend_service.controller.response.department.PositionByDepartmentResponse;
import vn.edu.stu.backend_service.controller.response.employee.EmployeeResponse;
import vn.edu.stu.backend_service.controller.response.position.PositionResponse;
import vn.edu.stu.backend_service.model.DepartmentEntity;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.PositionEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {
    private final ModelMapper modelMapper;

    public DepartmentResponse toMapDepartment(DepartmentEntity departmentEntity) {
        return modelMapper.map(departmentEntity, DepartmentResponse.class);
    }

    public List<DepartmentResponse> toMapListDepartment(List<DepartmentEntity> departmentEntityList) {
        return departmentEntityList.stream().map((department)->
                modelMapper.map(department, DepartmentResponse.class)
                ).toList();
    }

    public EmployeeByDepartmentResponse toMapEmployeeByDepartment(List<EmployeeEntity> employeeEntity) {
        EmployeeByDepartmentResponse employee = new EmployeeByDepartmentResponse();

        List<EmployeeResponse> list = new ArrayList<>();
        for (EmployeeEntity entity : employeeEntity) {
            EmployeeResponse employeeResponse = modelMapper.map(entity, EmployeeResponse.class);
            employeeResponse.setPositionName(entity.getPosition().getName());
            list.add(employeeResponse);
        }
        employee.setEmployees(list);
        return employee;
    }

    public PositionByDepartmentResponse toMapPositionByDepartment(List<PositionEntity> positionEntity) {
        PositionByDepartmentResponse position = new PositionByDepartmentResponse();
        position.setPositions(positionEntity.stream().map((e)->
                modelMapper.map(e, PositionResponse.class)).toList());
        return position;
    }
}

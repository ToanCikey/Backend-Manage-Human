package vn.edu.stu.backend_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.backend_service.controller.response.department.DepartmentResponse;
import vn.edu.stu.backend_service.model.DepartmentEntity;

import java.util.List;

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
}

package vn.edu.stu.backend_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.backend_service.controller.response.salary.SalaryResponse;
import vn.edu.stu.backend_service.model.SalaryEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SalaryMapper {
    private final ModelMapper modelMapper;

    public SalaryResponse toMapSlaResponse(SalaryEntity salaryEntity) {
        SalaryResponse salaryResponse = new SalaryResponse();
        modelMapper.map(salaryEntity, salaryResponse);

        if(salaryEntity.getEmployee() != null) {
            salaryResponse.setEmployeeId(salaryEntity.getEmployee().getId());
        }

        return salaryResponse;
    }

    public List<SalaryResponse> toMapSalaryList(List<SalaryEntity> salaryEntityList) {
        return salaryEntityList.stream().map((salary) ->
                modelMapper.map(salary, SalaryResponse.class)
                ).toList();
    }
}

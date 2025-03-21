package vn.edu.stu.backend_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.backend_service.controller.response.position.PositionResponse;
import vn.edu.stu.backend_service.model.PositionEntity;

import java.util.List;


@Component
@RequiredArgsConstructor
public class PositionMapper {
    private final ModelMapper modelMapper;

    public PositionResponse toPositionRespone(PositionEntity position) {
        return modelMapper.map(position, PositionResponse.class);
    }

    public List<PositionResponse> toPositionResponesList(List<PositionEntity> positionEntities) {
        return positionEntities.stream().map((position)->
                modelMapper.map(position, PositionResponse.class))
                .toList();
    }
}

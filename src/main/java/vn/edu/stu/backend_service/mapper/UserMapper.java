package vn.edu.stu.backend_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import vn.edu.stu.backend_service.controller.response.user.UserRespone;
import vn.edu.stu.backend_service.model.UserEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserRespone toUserRespone(UserEntity user) {
        return modelMapper.map(user, UserRespone.class);
    }

    public List<UserRespone> toListUserRespone(List<UserEntity> users) {
        return users.stream()
                .map(user -> modelMapper.map(user, UserRespone.class))
                .toList();
    }


}

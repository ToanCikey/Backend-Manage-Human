package vn.edu.stu.backend_service.service.impl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.request.UserUpdateRequest;
import vn.edu.stu.backend_service.model.UserEntity;
import vn.edu.stu.backend_service.repository.UserRepository;
import vn.edu.stu.backend_service.service.UserService;

@Slf4j(topic = "USER-SERVICE")
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public long saveUser(UserCreationRequest user) {
        log.info("Saving user {}",user);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setStatus(UserStatus.NONE);
        userEntity.setType(user.getType());

        userRepository.save(userEntity);
        log.info("Saved user {}",user);

        return userEntity.getId();
    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public void changePassword() {

    }

    @Override
    public void updateUser(UserUpdateRequest user) {

    }
}

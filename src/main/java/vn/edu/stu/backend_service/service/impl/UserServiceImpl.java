package vn.edu.stu.backend_service.service.impl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.request.UserUpdateRequest;
import vn.edu.stu.backend_service.exception.InvalidDataException;
import vn.edu.stu.backend_service.model.UserEntity;
import vn.edu.stu.backend_service.repository.UserRepository;
import vn.edu.stu.backend_service.service.UserService;

import java.util.Optional;

@Slf4j(topic = "USER-SERVICE")
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public long saveUser(UserCreationRequest user) {
        log.info("Saving user {}",user);
        Optional<UserEntity> userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail.isPresent()){
            throw new InvalidDataException("Email already exists");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setEmail(user.getEmail());
        userEntity.setStatus(UserStatus.ACTIVE);
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

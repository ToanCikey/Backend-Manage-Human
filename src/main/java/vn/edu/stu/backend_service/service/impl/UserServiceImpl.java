package vn.edu.stu.backend_service.service.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.request.UserUpdateRequest;
import vn.edu.stu.backend_service.exception.InvalidDataException;
import vn.edu.stu.backend_service.exception.ResourceNotFoundException;
import vn.edu.stu.backend_service.model.UserEntity;
import vn.edu.stu.backend_service.repository.UserRepository;
import vn.edu.stu.backend_service.service.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j(topic = "USER-SERVICE")
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity saveUser(UserCreationRequest user) {
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

        return userEntity;
    }

    @Override
    public void deleteUser(long id) {
        UserEntity userEntity = getUserById(id);
        userRepository.delete(userEntity);
    }

    @Override
    public void updateUser(UserUpdateRequest user) {
        UserEntity userEntity = getUserByEmail(user.getEmail());
        userEntity.setType(user.getType());
        userEntity.setStatus(user.getStatus());
        userRepository.save(userEntity);
    }

    @Override
    public void changePassword() {

    }



    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));
    }

    @Override
    public UserEntity getUserByUserName(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User not found"));
    }

    @Override
    public UserEntity getUserById(long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}

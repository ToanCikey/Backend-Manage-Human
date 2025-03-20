package vn.edu.stu.backend_service.service.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.request.UserUpdateRequest;
import vn.edu.stu.backend_service.controller.response.UserPageResponse;
import vn.edu.stu.backend_service.controller.response.UserRespone;
import vn.edu.stu.backend_service.exception.InvalidDataException;
import vn.edu.stu.backend_service.exception.ResourceNotFoundException;
import vn.edu.stu.backend_service.mapper.UserMapper;
import vn.edu.stu.backend_service.model.UserEntity;
import vn.edu.stu.backend_service.repository.UserRepository;
import vn.edu.stu.backend_service.service.UserService;
import vn.edu.stu.backend_service.specification.UserSpecification;

import java.util.List;
import java.util.Optional;

@Slf4j(topic = "USER-SERVICE")
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

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

    @Override
    public UserPageResponse getAllUsers(String keyword, String sort, int page, int size) {
        Specification<UserEntity> spec = Specification.where(UserSpecification.hasKeyword(keyword));

        Sort.Direction direction = Sort.Direction.ASC;
        if ("desc".equalsIgnoreCase(sort)) {
            direction = Sort.Direction.DESC;
        }
        Sort sortBy = Sort.by(direction, "id");
        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<UserEntity> users = userRepository.findAll(spec, pageable);

        List<UserRespone> userResponses = userMapper.toListUserRespone(users.getContent());

        UserPageResponse userPageResponse = new UserPageResponse();
        userPageResponse.setUsers(userResponses);
        userPageResponse.setPageNumber(users.getNumber());
        userPageResponse.setPageSize(users.getSize());
        userPageResponse.setTotalPages(users.getTotalPages());
        userPageResponse.setTotalElements(users.getNumberOfElements());

        return userPageResponse;
    }


}

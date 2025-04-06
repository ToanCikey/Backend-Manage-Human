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
import vn.edu.stu.backend_service.controller.request.PassWordRequest;
import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.request.UserUpdateRequest;
import vn.edu.stu.backend_service.controller.response.user.UserPageResponse;
import vn.edu.stu.backend_service.controller.response.user.UserRespone;
import vn.edu.stu.backend_service.exception.InvalidDataException;
import vn.edu.stu.backend_service.exception.ResourceNotFoundException;
import vn.edu.stu.backend_service.mapper.UserMapper;
import vn.edu.stu.backend_service.model.EmployeeEntity;
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
    public void changePassword(PassWordRequest request) {
        UserEntity userEntity = getUserByEmail(request.getEmail());

        if(userEntity != null){
            if(passwordEncoder.matches(request.getOldPassword(), userEntity.getPassword())){
                if(request.getNewPassword().equals(request.getConfirmPassword())){
                    userEntity.setPassword(passwordEncoder.encode(request.getNewPassword()));
                    userRepository.save(userEntity);
                }else {
                    throw new InvalidDataException("Password and Confirm password do not match");
                }
            }else {
                throw new InvalidDataException("Incorrect password");
            }
        }

    }



    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User with email = " + email +" not found"));
    }

    @Override
    public UserEntity getUserByUserName(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User with username = " + username +" not found"));
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with id = " + id +" not found"));
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserPageResponse getAllUsers(String keyword, String sort, int page, int size) {
        Specification<UserEntity> spec = Specification.where(UserSpecification.hasKeyword(keyword));

        Sort.Direction direction = ("desc".equalsIgnoreCase(sort)) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sortBy = Sort.by(direction, "id");

        Pageable pageable = PageRequest.of(page, size, sortBy);
        Page<UserEntity> users = userRepository.findAll(spec, pageable);

        List<UserRespone> userResponses = userMapper.toListUserRespone(users.getContent());

        UserPageResponse userPageResponse = new UserPageResponse();
        userPageResponse.setUsers(userResponses);
        userPageResponse.setPageNumber(users.getNumber());
        userPageResponse.setPageSize(users.getSize());
        userPageResponse.setTotalPages(users.getTotalPages());
        userPageResponse.setTotalElements(users.getTotalElements());

        return userPageResponse;
    }

    @Override
    public UserEntity getUserByDetail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("User with email = " + email +" not found"));
    }

    @Override
    public EmployeeEntity getEmployeeByUserId(Long id) {
        UserEntity userEntity = getUserById(id);

        if(userEntity != null){
            return userEntity.getEmployee();
        }
        return null;
    }
}

package vn.edu.stu.backend_service.service;

import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.request.UserUpdateRequest;
import vn.edu.stu.backend_service.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity saveUser(UserCreationRequest user);
    void deleteUser(long id);
    void changePassword();
    void updateUser(UserUpdateRequest user);
    UserEntity getUserByEmail(String email);
    UserEntity getUserByUserName(String username);
    UserEntity getUserById(long id);
    List<UserEntity> getAllUsers();
}

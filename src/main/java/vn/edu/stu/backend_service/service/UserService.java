package vn.edu.stu.backend_service.service;

import vn.edu.stu.backend_service.controller.request.PassWordRequest;
import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.request.UserUpdateRequest;
import vn.edu.stu.backend_service.controller.response.user.UserPageResponse;
import vn.edu.stu.backend_service.model.EmployeeEntity;
import vn.edu.stu.backend_service.model.UserEntity;

import java.util.List;


public interface UserService {
    UserEntity saveUser(UserCreationRequest user);
    void deleteUser(long id);
    void changePassword(PassWordRequest request);
    void updateUser(UserUpdateRequest user);
    UserEntity getUserByEmail(String email);
    UserEntity getUserByUserName(String username);
    UserEntity getUserById(Long id);
    List<UserEntity> getAllUsers();
    UserPageResponse getAllUsers(String keyword, String sort, int page, int size);
    UserEntity getUserByDetail(String email);
    EmployeeEntity getEmployeeByUserId(Long id);
}

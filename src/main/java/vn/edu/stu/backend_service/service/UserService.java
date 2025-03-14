package vn.edu.stu.backend_service.service;

import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.request.UserUpdateRequest;

public interface UserService {
    long saveUser(UserCreationRequest user);
    void deleteUser(long id);
    void changePassword();
    void updateUser(UserUpdateRequest user);
}

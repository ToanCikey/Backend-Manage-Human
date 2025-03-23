package vn.edu.stu.backend_service.service;


import vn.edu.stu.backend_service.controller.request.LoginRequest;
import vn.edu.stu.backend_service.model.UserEntity;

public interface AuthService {
    UserEntity login(LoginRequest loginRequest);
}

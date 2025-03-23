package vn.edu.stu.backend_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.stu.backend_service.controller.request.LoginRequest;
import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.response.ResponseSuccess;
import vn.edu.stu.backend_service.controller.response.user.UserRespone;
import vn.edu.stu.backend_service.mapper.UserMapper;
import vn.edu.stu.backend_service.model.UserEntity;
import vn.edu.stu.backend_service.service.AuthService;
import vn.edu.stu.backend_service.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth Controller")
@Slf4j(topic = "AUTH-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthService authService;
    private final UserMapper userMapper;
    private final UserService userService;

    @Operation(summary = "Login", description = "API login user ")
    @PostMapping("login")
    public ResponseSuccess<UserRespone> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Login user: {}", loginRequest);
        UserEntity userEntity = authService.login(loginRequest);
        return new ResponseSuccess<>(HttpStatus.OK.value(), "Login success", userMapper.toUserRespone(userEntity));
    }

    @Operation(summary = "Create User", description = "API add new user to database")
    @PostMapping("/register")
    public ResponseSuccess<UserRespone> register(@Valid @RequestBody UserCreationRequest request) {
        log.info("Create User: {}", request);
        UserEntity user = userService.saveUser(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create User successful", userMapper.toUserRespone(user));
    }
}

package vn.edu.stu.backend_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.backend_service.controller.request.LoginRequest;
import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.response.ResponseSuccess;
import vn.edu.stu.backend_service.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller")
@Slf4j(topic = "USER-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Create User", description = "API add new user to database")
    @PostMapping("/add")
    public ResponseSuccess<Long> createUser(@Valid @RequestBody UserCreationRequest request) {
        log.info("Create User: {}", request);
        long id = userService.saveUser(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create User successful",id);
    }

    @Operation(summary = "Login", description = "API login to database")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        log.info("login: {}", request);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, Object> result = new HashMap<>();
        result.put("status", HttpStatus.CREATED.value());
        result.put("message", "User created successfully");
        result.put("data","1");
        return ResponseEntity.ok("Login Successful");
    }
}

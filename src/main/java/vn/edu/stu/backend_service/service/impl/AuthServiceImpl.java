package vn.edu.stu.backend_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import vn.edu.stu.backend_service.controller.request.LoginRequest;
import vn.edu.stu.backend_service.model.UserEntity;
import vn.edu.stu.backend_service.service.AuthService;
import vn.edu.stu.backend_service.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public UserEntity login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            return userService.getUserByEmail(loginRequest.getEmail());
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Email or password is incorrect");
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during authentication", e);
        }
    }

}

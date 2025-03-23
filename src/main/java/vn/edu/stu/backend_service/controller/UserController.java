package vn.edu.stu.backend_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.edu.stu.backend_service.controller.request.UserCreationRequest;
import vn.edu.stu.backend_service.controller.request.UserUpdateRequest;
import vn.edu.stu.backend_service.controller.response.ResponseSuccess;
import vn.edu.stu.backend_service.controller.response.user.UserPageResponse;
import vn.edu.stu.backend_service.controller.response.user.UserRespone;
import vn.edu.stu.backend_service.mapper.UserMapper;
import vn.edu.stu.backend_service.model.UserEntity;
import vn.edu.stu.backend_service.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User Controller")
@Slf4j(topic = "USER-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    @Operation(summary = "GetAll user by search, sort, page", description = "API get all users to database")
    @GetMapping("/listbypage")
    public ResponseSuccess<UserPageResponse> getAllUsersByPageAndSort(@RequestParam(required = false) String keyword,
                                                                      @RequestParam(required = false) String sort,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size) {
        log.info("GetAll User by page and sort: {}");
        UserPageResponse userPageResponse = userService.getAllUsers(keyword, sort, page, size);
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all user by page and sort successful", userPageResponse);
    }

    @Operation(summary = "GetAll User", description = "API get all users to database")
    @GetMapping("/list")
    public ResponseSuccess<List<UserRespone>> getAllUsers() {
        log.info("GetAll User: {}");
        List<UserEntity> users = userService.getAllUsers();
        return new ResponseSuccess<>(HttpStatus.OK.value(),"Get all user successful", userMapper.toListUserRespone(users));
    }

    @Operation(summary = "Create User", description = "API add new user to database")
    @PostMapping("/add")
    public ResponseSuccess<UserRespone> createUser(@Valid @RequestBody UserCreationRequest request) {
        log.info("Create User: {}", request);
        UserEntity user = userService.saveUser(request);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(),"Create User successful", userMapper.toUserRespone(user));
    }

    @Operation(summary = "Delete User", description = "API delete user to database")
    @DeleteMapping("/delete/{id}")
    public ResponseSuccess<UserEntity> deleteUser(@Min(1) @PathVariable Long id) {
        log.info("Delete User: {}", id);
        userService.deleteUser(id);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Delete User successful");
    }

    @Operation(summary = "Update User", description = "API update user to database")
    @PutMapping("/update")
    public ResponseSuccess<UserEntity> updateUser(@Valid @RequestBody UserUpdateRequest request) {
        log.info("Update User: {}", request);
        userService.updateUser(request);
        return new ResponseSuccess<>(HttpStatus.NO_CONTENT.value(),"Update User successful");
    }
}

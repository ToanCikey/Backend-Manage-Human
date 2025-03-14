package vn.edu.stu.backend_service.controller.response;

import lombok.*;
import vn.edu.stu.backend_service.common.UserStatus;
import vn.edu.stu.backend_service.common.UserType;

import java.io.Serializable;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRespone implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String email;
    private UserType type;
    private UserStatus status;
}

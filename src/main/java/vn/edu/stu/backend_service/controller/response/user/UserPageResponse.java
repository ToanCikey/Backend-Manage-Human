package vn.edu.stu.backend_service.controller.response.user;

import lombok.*;
import vn.edu.stu.backend_service.controller.response.PageResponseAbstract;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter

public class UserPageResponse extends PageResponseAbstract implements Serializable {
    private List<UserRespone> users;
}

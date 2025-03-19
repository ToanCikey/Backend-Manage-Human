package vn.edu.stu.backend_service.controller.response;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ResponseError {
    private Date timestamp;
    private int status;
    private String path;
    private String error;
    private String message;
}

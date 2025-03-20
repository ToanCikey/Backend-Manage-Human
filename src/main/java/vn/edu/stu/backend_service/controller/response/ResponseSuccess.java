package vn.edu.stu.backend_service.controller.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseSuccess<T> {
    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseSuccess(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseSuccess(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

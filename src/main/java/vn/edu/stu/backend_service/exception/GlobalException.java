package vn.edu.stu.backend_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.edu.stu.backend_service.controller.response.ResponseError;

import java.util.Date;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleValidationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ResponseError errorResponse = new ResponseError();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getBindingResult().getFieldError().getDefaultMessage());
        return errorResponse;
    }

    @ExceptionHandler({InvalidDataException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseError handleInValidationException(Exception e, HttpServletRequest request) {
        ResponseError errorResponse = new ResponseError();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler({UsernameNotFoundException.class, ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError handleInValidationResourceException(Exception e, HttpServletRequest request) {
        ResponseError errorResponse = new ResponseError();
        errorResponse.setTimestamp(new Date());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setPath(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }
}

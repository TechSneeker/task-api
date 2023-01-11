package br.com.tasks.taskapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler extends Exception {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> launcherCustomException(CustomException ex) {
        return new ResponseEntity<>(ex, new HttpHeaders(), ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> launcherMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status) {

        String field = "'" + ex.getBindingResult().getFieldErrors().get(0).getField() + "' ";
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        String message = "The field " + field + errorMessage;

        var exception = CustomException.builder()
                .status(status)
                .message(message)
                .build();

        return new ResponseEntity<>(exception, headers, status);
    }
}

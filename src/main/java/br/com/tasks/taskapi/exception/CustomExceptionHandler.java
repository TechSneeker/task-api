package br.com.tasks.taskapi.exception;

import org.modelmapper.ModelMapper;
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

    private final ModelMapper mapper = new ModelMapper();

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> launcherCustomException(CustomException ex) {
        CustomExceptionMask mask = new CustomExceptionMask();

        mapper.map(ex, mask);

        return new ResponseEntity<>(mask, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> launcherMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status) {

        String field = "'" + ex.getBindingResult().getFieldErrors().get(0).getField() + "' ";
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        String message = "The field " + field + errorMessage;

        var exception = CustomExceptionMask.builder()
                .status(status)
                .message(message)
                .build();

        return new ResponseEntity<>(exception, headers, status);
    }
}

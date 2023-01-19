package br.com.tasks.taskapi.exception;

import jakarta.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final ModelMapper mapper = new ModelMapper();

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> launcherCustomException(CustomException ex) {
        CustomExceptionMask mask = new CustomExceptionMask();

        mapper.map(ex, mask);

        return new ResponseEntity<>(mask, ex.getStatus());
    }

    //Jakarta
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> launcherConstraintViolationException(ConstraintViolationException ex) {

        Map<String, String> map = new HashMap<>();

        ex.getConstraintViolations().forEach((field) ->
                map.put(field.getPropertyPath().toString(), field.getMessageTemplate()));

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    //Javax
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode code, WebRequest request) {

        String field = "'" + ex.getBindingResult().getFieldErrors().get(0).getField() + "' ";
        String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        String message = "The field " + field + errorMessage;
        HttpStatus status = HttpStatus.valueOf(code.value());

        var exception = CustomExceptionMask.builder()
                .status(status)
                .message(message)
                .build();

        return new ResponseEntity<>(exception, headers, status);
    }

}

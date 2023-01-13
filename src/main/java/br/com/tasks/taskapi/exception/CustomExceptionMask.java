package br.com.tasks.taskapi.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomExceptionMask {
    private HttpStatus status;
    private String message;
}

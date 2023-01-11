package br.com.tasks.taskapi.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomException extends Exception {
    private HttpStatus status;
    private String message;
}

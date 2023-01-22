package br.com.tasks.taskapi.entity.enums;

import br.com.tasks.taskapi.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Priority {
    HIGH("HIGH"),
    MEDIUM("MEDIUM"),
    LOW("LOW");

    String value;

    Priority(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Priority get(String value) throws CustomException {

        try {
            return Arrays.stream(Priority.values()).filter(priority ->
                    StringUtils.containsIgnoreCase(priority.getValue(), value)).findAny().get();
        } catch (NoSuchElementException ex) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    "Unexpected value. expected: [HIGH, MEDIUM, LOW]");
        }
    }
}

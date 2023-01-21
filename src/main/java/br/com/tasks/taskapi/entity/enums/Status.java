package br.com.tasks.taskapi.entity.enums;

import br.com.tasks.taskapi.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Status {

    TODO("TODO"),
    DOING("DOING"),
    PAUSE("PAUSE"),
    DONE("DONE");

    String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Status get(String value) throws CustomException {

        try {
            return Arrays.stream(Status.values()).filter(status ->
                    StringUtils.containsIgnoreCase(status.getValue(), value)).findAny().get();
        } catch (NoSuchElementException ex) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    "Unexpected value. expected: [TODO,DOING,PAUSE,DONE]");
        }
    }
}

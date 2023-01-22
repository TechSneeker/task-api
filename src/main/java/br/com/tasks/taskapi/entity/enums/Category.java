package br.com.tasks.taskapi.entity.enums;

import br.com.tasks.taskapi.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Category {
    FEATURE("FEATURE"),
    IMPROVEMENT("IMPROVEMENT"),
    REFACTOR("REFACTOR"),
    BUG("BUG");

    String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Category get(String value) throws CustomException {

        try {
            return Arrays.stream(Category.values()).filter(category ->
                    StringUtils.containsIgnoreCase(category.getValue(), value)).findAny().get();
        } catch (NoSuchElementException ex) {
            throw new CustomException(HttpStatus.BAD_REQUEST,
                    "Unexpected value. expected: [FEATURE,IMPROVEMENT,REFACTOR,BUG]");
        }
    }
}

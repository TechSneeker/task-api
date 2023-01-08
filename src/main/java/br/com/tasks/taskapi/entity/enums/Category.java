package br.com.tasks.taskapi.entity.enums;

import java.util.Arrays;

public enum Category {
    FEATURE("FEATURE"),
    IMPROVEMENT("IMPROVEMENT"),
    REFACTOR("REFACTOR"),
    BUG("BUG");

    String value;

    Category(String value) {
        this.value = value;
    }

    public static Priority get(String value) {
        return Arrays.stream(Priority.values())
                .filter(priority -> priority.getValue()
                        .equalsIgnoreCase(value)).findAny().get();
    }
}

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

    public String getValue() {
        return value;
    }

    public static Category get(String value) {
        return Arrays.stream(Category.values())
                .filter(category -> category.getValue()
                        .equalsIgnoreCase(value)).findAny().get();
    }
}

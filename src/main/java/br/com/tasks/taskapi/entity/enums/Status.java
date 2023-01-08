package br.com.tasks.taskapi.entity.enums;

import java.util.Arrays;

public enum Status {
    TODO("TODO"),
    DOING("DOING"),
    PAUSE("PAUSE"),
    DONE("DONE");

    String value;

    Status(String value) {
        this.value = value;
    }

    public static Priority get(String value) {
        return Arrays.stream(Priority.values())
                .filter(priority -> priority.getValue()
                        .equalsIgnoreCase(value)).findAny().get();
    }
}

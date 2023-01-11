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

    public String getValue() {
        return value;
    }

    public static Status get(String value) {
        return Arrays.stream(Status.values())
                .filter(status -> status.getValue()
                        .equalsIgnoreCase(value)).findAny().get();
    }
}

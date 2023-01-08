package br.com.tasks.taskapi.entity.enums;

import java.util.Arrays;

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

    public static Priority get(String value) {
       return Arrays.stream(Priority.values())
               .filter(priority -> priority.getValue()
                       .equalsIgnoreCase(value)).findAny().get();
    }
}

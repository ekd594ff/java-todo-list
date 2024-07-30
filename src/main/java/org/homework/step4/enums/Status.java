package org.homework.step4.enums;

import java.util.Arrays;

public enum Status {
    COMPLETE("완료"), INCOMPLETE("미완료"), IN_PROGRESS("진행중");
    private final String value;
    Status(String value) {
        this.value = value;
    }

    public static Status of(String value) {
        return Arrays.stream(values())
                .filter(item -> item.value.equals(value))
                .findFirst()
                .orElse(null);
    }

    public String getValue(){
        return this.value;
    }
}

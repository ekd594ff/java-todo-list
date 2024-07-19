package org.homework.step2.enums;

public enum Status {
    COMPLETE("완료"), INCOMPLETE("미완료"), IN_PROGRESS("진행중");
    private final String value;
    Status(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}

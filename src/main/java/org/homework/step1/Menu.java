package org.homework.step1;

public enum Menu {
    NONE(0), ADD(1), DELETE(2), SELECT(3), EXIT(-1);
    private int value;
    Menu(int value) {
        this.value = value;
    }
}

package org.homework;

import java.util.Arrays;

public enum InputMenu {
    ADD("1"), DELETE("2"), VIEW("3"), EXIT("4"), NONE("5");

    private final String input;

    InputMenu(String input) {
        this.input = input;
    }

    public static InputMenu of(String input) {
        InputMenu[] values = values();
        for(InputMenu value : values) {
            if(value.input.equals(input)) {
                return value;
            }
        }
        return NONE;
    }

    public static InputMenu of2(String input) {
        return Arrays.stream(values())
                .filter(inputMenu -> inputMenu.input.equals(input))
                .findAny()
                .orElse(NONE);
    }
}

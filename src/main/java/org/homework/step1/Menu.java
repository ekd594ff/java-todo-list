package org.homework.step1;

import java.util.Arrays;

public enum Menu {
    NONE(0), ADD(1), DELETE(2), SELECT(3), EXIT(4);
    private final int value;
    private static final String[] kor = {"추가", "삭제", "조회", "종료"};

    Menu(int value) {
        this.value = value;
    }

    public static Menu get(int value) {
        return Arrays.stream(values())
                .filter(item -> item.value == value)
                .findAny()
                .orElse(NONE);
    }

    public static String[] getMenuKorList() {
        return Arrays.stream(values()).filter(item -> item.value > 0).map(item -> kor[item.value - 1]).toArray(String[]::new);
    }

    public static Integer[] getMenuValueList() {
        return Arrays.stream(values()).filter(item -> item.value > 0).map(item -> item.value).toArray(Integer[]::new);
    }

    public static String getMenuKor(int value) {
        return kor[value];
    }
}

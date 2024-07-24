package org.homework.step3.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {
    NONE(0, ""),
    ADD(1, "추가"),
    DELETE(2, "삭제"),
    SELECT(3, "조회"),
    UPDATE(4, "수정"),
    LIST(5, "목록"),
    SEARCH(6, "검색"),
    EXIT(7, "종료");

    private final int value;
    private final String korname;

    Menu(int value, String name) {
        this.value = value;
        this.korname = name;
    }

    public static Menu get(int value) {
        return Arrays.stream(values())
                .filter(item -> item.value == value)
                .findAny()
                .orElse(NONE);
    }

    public static List<String> getMenuKorList() {
        return Arrays.stream(values())
                .filter(item -> item.value > 0)
                .map(item -> item.korname)
                .collect(Collectors.toList());
    }
}

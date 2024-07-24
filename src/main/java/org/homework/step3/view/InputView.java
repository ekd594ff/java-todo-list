package org.homework.step3.view;

import org.homework.step3.dto.CreateTodoDTO;
import org.homework.step3.enums.Menu;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private final Scanner scan = new Scanner(System.in);

    private Integer toInteger(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException numberFormatException) {
            throw new NumberFormatException("잘못된 입력값 입니다");
        }
    }

    private int checkValueNegative(int value) {
        if (value < 0) {
            throw new InputMismatchException();
        }
        return value;
    }

    public int getUpdatedTodoId() {
        while (true) {
            System.out.print("수정할 ID: ");
            try {
                return Integer.parseInt(this.getLine());
            } catch (NumberFormatException e) {
                System.out.println("올바른 ID를 입력해 주세요");
            }
        }
    }

    public int getDeletedTodoId() {
        while (true) {
            System.out.print("삭제할 ID: ");
            try {
                return Integer.parseInt(this.getLine());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("올바른 ID를 입력해 주세요");
            }
        }
    }

    public String getDescription() {
        System.out.print("할 일: ");
        return this.getLine();
    }

    public LocalDateTime getDeadline() throws DateTimeException, InputMismatchException {
        System.out.print("마감일(년 월 일, 공백, 쉼표, - , / 으로 구분): ");
        List<Integer> list = Arrays
                .stream(this.getLine().split("[\\s-/,]+"))
                .map(this::toInteger)
                .map(this::checkValueNegative)
                .toList();
        if (list.size() != 3) {
            throw new InputMismatchException("년, 월, 일 항목을 입력해 주세요");
        }
        return LocalDateTime.of(list.get(0), list.get(1), list.get(2), 0, 0, 0);
    }

    public CreateTodoDTO getCreateTodoDTO() {
        return new CreateTodoDTO(this.getDescription(), this.getDeadline());
    }

    public int getSelectTodoId() {
        while (true) {
            System.out.print("조회할 일의 ID: ");
            try {
                return Integer.parseInt(this.getLine());
            } catch (NumberFormatException e) {
                System.out.println("올바른 ID를 입력해 주세요");
            }
        }
    }

    public String printWrongMenuInput() {
        return "잘못된 입력입니다";
    }

    public String getLine() {
        return scan.nextLine().trim();
    }

    public Menu getMenu(String line) {
        try {
            return Menu.get(Integer.parseInt(line));
        } catch (Exception e) {
            return Menu.NONE;
        }
    }

    public void printMenu() {
        List<String> menuList = Menu.getMenuKorList();
        System.out.println(IntStream.rangeClosed(0, menuList.size() - 1)
                .mapToObj((index) -> " " + (index + 1) + "." + menuList.get(index))
                .collect(Collectors.joining(",")));
    }

    public String getKeyword() {
        System.out.print("검색어 입력: ");
        return this.getLine();
    }
}

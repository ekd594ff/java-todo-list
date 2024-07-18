package org.homework.step2.view;

import org.homework.step2.enums.Menu;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private final Scanner scan = new Scanner(System.in);


    public int getUpdatedTodoId() {
        while(true) {
            System.out.print("수정할 ID: ");
            try {
                return Integer.parseInt(this.getLine());
            } catch (NumberFormatException e) {
                System.out.println("올바른 ID를 입력해 주세요");
            }
        }
    }

    public int getDeletedTodoId() {
        while(true) {
            System.out.print("삭제할 ID: ");
            try {
                return Integer.parseInt(this.getLine());
            } catch (NumberFormatException e) {
                System.out.println("올바른 ID를 입력해 주세요");
            }
        }
    }

    public String getDescription() {
        System.out.print("할 일: ");
        return this.getLine();
    }

    public int getSelectTodoId() {
        while(true) {
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
}

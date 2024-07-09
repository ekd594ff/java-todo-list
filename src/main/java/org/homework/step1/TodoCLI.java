package org.homework.step1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class TodoCLI {

    private final Scanner scan = new Scanner(System.in);
    private final Map<Integer, String> map = new HashMap<>();
    private int num = 1;

    public int getNum() {
        return num;
    }

    public void incrementNum() {
        ++num;
    }

    private void add() {
        System.out.print("할 일: ");
        String line = scan.nextLine().trim();
        int newId = getNum();
        map.put(newId, line);
        System.out.println("할 일이 추가되었습니다. ID: " + newId);
        incrementNum();
    }

    private void delete() {

    }

    private void printMenu() {
        System.out.println(String.join(", ",
                Arrays.stream(Menu.getMenuValueList())
                        .map(value -> value - 1)
                        .map(index -> (index + 1) + "." + Menu.getMenuKor(index))
                        .toArray(String[]::new)));
    }

    private Menu getMenu() {
        String line = scan.nextLine().trim();
        try {
            return Menu.get((int)Integer.parseInt(line));
        } catch (Exception e) {
            return Menu.NONE;
        }
    }

    private void executeMethod(Menu choice) {
        switch (choice) {
            case ADD:
                this.add();
                break;
            case DELETE:
                break;
            case SELECT:
                break;
            case EXIT:
                break;
            default:
                System.out.println("잘못된 입력입니다");
                break;
        }
    }

    public void cli() {
        while (true) {
            printMenu();
            Menu choice = getMenu();
            executeMethod(choice);
            break;
        }
    }
}

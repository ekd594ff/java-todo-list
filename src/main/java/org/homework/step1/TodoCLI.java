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

    private void select() {
        System.out.print("조회할 일의 ID: ");
        try {
            int id = Integer.parseInt(scan.nextLine().trim());
            String todo = map.get(id);
            if (todo == null) {
                System.out.println("해당 ID의 할 일이 없습니다");
                return;
            }
            System.out.println("할 일 ID: " + id +  " 내용: " + todo);
        }catch (Exception e){
            System.out.println("해당 ID의 할 일이 없습니다");
        }
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
        System.out.print("삭제할 ID: ");
        String line = scan.nextLine().trim();
        try {
            int id = Integer.parseInt(line);
            String result = map.remove(id);
            if (result == null) {
                System.out.println("해당 ID의 할 일이 없습니다");
                return;
            }
            System.out.println("할 일이 삭제되었습니다. ID: " + id);
        } catch (Exception e) {
            System.out.println("해당 ID의 할 일이 없습니다");
        }
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
            return Menu.get((int) Integer.parseInt(line));
        } catch (Exception e) {
            return Menu.NONE;
        }
    }

    private boolean executeMethod(Menu choice) {
        switch (choice) {
            case ADD:
                this.add();
                break;
            case DELETE:
                this.delete();
                break;
            case SELECT:
                this.select();
                break;
            case EXIT:
                return true;
            default:
                System.out.println("잘못된 입력입니다");
                break;
        }
        return false;
    }

    public void cli() {
        while (true) {
            printMenu();
            Menu choice = getMenu();
            if(executeMethod(choice)) {
                break;
            }
        }
    }
}

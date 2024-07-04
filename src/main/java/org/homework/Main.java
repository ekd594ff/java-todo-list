package org.homework;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();

        InputMenu inputMenu = InputMenu.of(line);
    }
}
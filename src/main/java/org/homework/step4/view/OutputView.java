package org.homework.step4.view;

import org.homework.step4.entity.Todo;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OutputView {

    private String listFormatString() {
        return "%-8s %-64s %-8s %-16s\n";
    }

    private void printColumns() {
        String[] columns = {"ID", "내용", "상태", "마감일"};
        System.out.printf(this.listFormatString(), columns[0], columns[1], columns[2], columns[3]);
        System.out.println("==============================================================================================");
    }

    private void printTuple(Todo todo) {
        System.out.printf(this.listFormatString(),
                todo.getId(),
                todo.getDescription(),
                todo.getStatus().getValue(),
                todo.getDeadline().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    public void selectSuccess(Todo todo) {
        this.printColumns();
        this.printTuple(todo);
    }

    public void deleteSuccess(Todo todo) {
        System.out.println("할 일이 삭제되었습니다. ID: " + todo.getId());
    }


    public void insertSuccess(Todo todo) {
        System.out.println("할 일이 추가되었습니다. ID: " + todo.getId());
    }

    public void updateSuccess(Todo todo) {
        this.printTuple(todo);
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public void printTodoList(List<Todo> list) {
        this.printColumns();
        list.forEach(this::printTuple);
    }

    public void printEmptySearchList() {
        System.out.println("검색 결과가 없습니다");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}

package org.homework.step2.view;

import org.homework.step2.Todo;

public class OutputView {

    public void selectFail() {
        System.out.println("해당 ID의 할 일이 없습니다");
    }
    public void selectSuccess(Todo todo){
        System.out.println("할 일 ID: " + todo.getId() + " 내용: " + todo.getDescription());
    }

    public void deleteSuccess(Todo todo) {
        System.out.println("할 일이 삭제되었습니다. ID: " + todo.getId());
    }

    public void deleteFail() {
        System.out.println("해당 ID의 할 일이 없습니다");
    }

    public void insertSuccess(Todo todo) {
        System.out.println("할 일이 추가되었습니다. ID: " + todo.getId());
    }

    public void updateSuccess(Todo todo) {
        System.out.println("할 일 ID: " + todo.getId() + " 내용: " + todo.setDescription(todo.getDescription()));
    }

    public void updateFail() {
        System.out.println("해당 ID의 할 일이 없습니다");
    }


}

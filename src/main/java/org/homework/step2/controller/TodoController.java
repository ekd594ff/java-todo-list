package org.homework.step2.controller;

import org.homework.step2.enums.Menu;
import org.homework.step2.service.TodoService;
import org.homework.step2.view.InputView;
import org.homework.step2.view.OutputView;

public class TodoController {

    private InputView inputView;
    private TodoService todoService;
    private OutputView outputView;

    public TodoController() {
        inputView = new InputView();
        todoService = new TodoService();
        outputView = new OutputView();
    }

    private String controller(Menu menu) {
        switch (menu) {
            case ADD:
                return todoService.insertTodo();
            case DELETE:
                return todoService.deleteTodo();
            case SELECT:
                return todoService.getTodo();
            case UPDATE:
                return todoService.updateTodo();
            case EXIT:
                return "EXIT";
            default:
                return inputView.printWrongMenuInput();
        }
    }

    public void run() {
        while(true) {
            inputView.printMenu();
            String result = this.controller(inputView.getMenu(inputView.getLine()));
            if(result.equals("EXIT")) {
                break;
            } else {
                outputView.printResultView(result);
            }
        }
    }
}

package org.homework.step2.controller;

import org.homework.step2.enums.Menu;
import org.homework.step2.service.TodoService;
import org.homework.step2.view.InputView;
import org.homework.step2.view.OutputView;

import java.util.Optional;

public class TodoController {

    private InputView inputView;
    private TodoService todoService;
    private OutputView outputView;

    public TodoController() {
        inputView = new InputView();
        todoService = new TodoService();
        outputView = new OutputView();
    }

    private boolean controller(Menu menu) {
        switch (menu) {
            case ADD:
                outputView.insertSuccess(todoService.insertTodo(inputView.getDescription()));
                break;
            case DELETE:
                Optional
                        .ofNullable(todoService.deleteTodo(inputView.getDeletedTodoId()))
                        .ifPresentOrElse(todo -> {
                            outputView.deleteSuccess(todo);
                        }, () -> {
                            outputView.deleteFail();
                        });
                break;
            case SELECT:
                Optional
                        .ofNullable(todoService.getTodo(inputView.getSelectTodoId()))
                        .ifPresentOrElse(todo -> {
                            outputView.selectSuccess(todo);
                        }, () -> {
                            outputView.selectFail();
                        });
                break;
            case UPDATE:
                Optional
                        .ofNullable(todoService.updateTodo(inputView.getUpdatedTodoId(),
                                inputView.getDescription())).ifPresentOrElse(todo -> {
                            outputView.updateSuccess(todo);
                        }, () -> {
                            outputView.updateFail();
                        });
                break;
            case EXIT:
                return true;
            default:
                inputView.printWrongMenuInput();
                break;
        }
        return false;
    }

    public void run() {
        do {
            inputView.printMenu();
        } while (!this.controller(inputView.getMenu(inputView.getLine())));
    }
}

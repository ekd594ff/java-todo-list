package org.homework.step3.controller;

import org.homework.step3.Todo;
import org.homework.step3.enums.Menu;
import org.homework.step3.service.TodoService;
import org.homework.step3.view.InputView;
import org.homework.step3.view.OutputView;

import java.time.DateTimeException;
import java.util.List;
import java.util.NoSuchElementException;

public class TodoController {

    private final InputView inputView;
    private final TodoService todoService;
    private final OutputView outputView;

    public TodoController(InputView inputView, TodoService todoService, OutputView outputView) {
        this.inputView = inputView;
        this.todoService = todoService;
        this.outputView = outputView;
    }

    private boolean controller(Menu menu) {
        switch (menu) {
            case ADD:
                outputView.insertSuccess(todoService.insertTodo(inputView.getCreateTodoDTO()));
                break;
            case DELETE:
                this.outputView.deleteSuccess(todoService.deleteTodo(inputView.getDeletedTodoId()));
                break;
            case SELECT:
                this.outputView.selectSuccess(this.todoService.getTodo(inputView.getSelectTodoId()));
                break;
            case UPDATE:
                outputView.updateSuccess(todoService.updateTodo(inputView.getUpdatedTodoId(), inputView.getDescription()));
                break;
            case LIST:
                this.outputView.printTodoList(this.todoService.getTodoList());
                break;
            case SEARCH:
                List<Todo> list = this.todoService.searchTodoList(this.inputView.getKeyword());
                if (!list.isEmpty()) {
                    this.outputView.printTodoList(list);
                } else {
                    this.outputView.printEmptySearchList();
                }
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
        while(true) {
            try {
                inputView.printMenu();
                if(this.controller(inputView.getMenu(inputView.getLine()))) {
                    break;
                }
            } catch (DateTimeException dateTimeException) {
                outputView.printExceptionMessage("잘못된 날짜 입력값입니다");
            } catch(NumberFormatException | NoSuchElementException exception) {
                outputView.printExceptionMessage(exception.getMessage());
            }
        };
    }
}

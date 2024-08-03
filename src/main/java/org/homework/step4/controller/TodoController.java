package org.homework.step4.controller;

import org.homework.step4.dto.CreateTodoDTO;
import org.homework.step4.entity.Todo;
import org.homework.step4.enums.Menu;
import org.homework.step4.service.TodoService;
import org.homework.step4.view.InputView;
import org.homework.step4.view.OutputView;

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
        Todo result;
        List<Todo> resultList;
        int todoId;
        switch (menu) {
            case ADD:
                CreateTodoDTO createTodoDTO = inputView.getCreateTodoDTO();
                result = todoService.insertTodo(createTodoDTO);
                outputView.insertSuccess(result);
                break;
            case DELETE:
                todoId = inputView.getTodoIdWithMessage("삭제할 ID");
                result = todoService.deleteTodo(todoId);
                this.outputView.deleteSuccess(result);
                break;
            case SELECT:
                todoId = inputView.getTodoIdWithMessage("조회할 ID");
                result = this.todoService.getTodo(todoId);
                this.outputView.selectSuccess(result);
                break;
            case UPDATE:
                todoId = inputView.getTodoIdWithMessage("수정할 ID");
                String description = inputView.getDescription();
                result = todoService.updateTodo(todoId, description);
                outputView.updateSuccess(result);
                break;
            case LIST:
                resultList = this.todoService.getTodoList();
                this.outputView.printTodoList(resultList);
                break;
            case SEARCH:
                String keyword = this.inputView.getKeyword();
                resultList = this.todoService.searchTodoList(keyword);
                if (!resultList.isEmpty()) {
                    this.outputView.printTodoList(resultList);
                } else {
                    this.outputView.printEmptySearchList();
                }
                break;
            case EXIT:
                todoService.clear();
                return true;
            default:
                this.outputView.printMessage(inputView.printWrongMenuInput());
                break;
        }
        return false;
    }

    public void run() {
        while (true) {
            try {
                inputView.printMenu();
                if (this.controller(inputView.getMenu(inputView.getLine()))) {
                    break;
                }
            } catch (DateTimeException dateTimeException) {
                outputView.printExceptionMessage("잘못된 날짜 입력값입니다");
            } catch (NumberFormatException | NoSuchElementException exception) {
                outputView.printExceptionMessage(exception.getMessage());
            }
        }
        ;
    }
}

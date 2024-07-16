package org.homework.step2.service;

import org.homework.step2.Todo;
import org.homework.step2.repository.TodoRepository;
import org.homework.step2.view.InputView;

import java.util.Optional;

public class TodoService {
    private final TodoRepository todoRepository = new TodoRepository();
    private final InputView inputView = new InputView();

    public String getTodo() {
        int todoId = inputView.getSelectTodoId();
        return Optional.ofNullable(todoRepository.select(todoId))
                .map(todo -> "할 일 ID: " + todo.getId() + " 내용: " + todo.getDescription())
                .orElse("해당 ID의 할 일이 없습니다");
    }

    public String insertTodo() {
        String description = inputView.getDescription();
        Todo result = todoRepository.insert(description);
        return "할 일이 추가되었습니다. ID: " + result.getId();
    }

    public String deleteTodo() {
        int todoId = inputView.getDeletedTodoId();
        return Optional.ofNullable(todoRepository.delete(todoId))
                .map(todo -> "할 일이 삭제되었습니다. ID: " + todo.getId())
                .orElse("해당 ID의 할 일이 없습니다");
    }

    public String updateTodo() {
        return todoRepository.update(inputView.getUpdatedTodoId(), inputView.getDescription());
    }
}

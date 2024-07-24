package org.homework.step2.service;

import org.homework.step2.Todo;
import org.homework.step2.repository.TodoRepository;
import org.homework.step2.view.InputView;

import java.util.Optional;

public class TodoService {
    private final TodoRepository todoRepository = new TodoRepository();


    public Todo getTodo(int todoId) {
        return todoRepository.select(todoId);
    }

    public Todo insertTodo(String description) {
        return todoRepository.insert(description);
    }

    public Todo deleteTodo(int todoId) {
        return todoRepository.delete(todoId);
    }

    public Todo updateTodo(int todoId, String description) {
        return todoRepository.update(todoId, description);
    }


}

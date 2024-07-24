package org.homework.step3.service;

import org.homework.step3.Todo;
import org.homework.step3.dto.CreateTodoDTO;
import org.homework.step3.repository.TodoRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class TodoService {
    private final TodoRepository todoRepository = new TodoRepository();


    public Todo getTodo(int todoId) {
        return todoRepository.select(todoId)
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 할 일이 없습니다"));
    }

    public Todo insertTodo(CreateTodoDTO createTodoDTO) {
        return todoRepository.insert(createTodoDTO);
    }

    public Todo deleteTodo(int todoId) {
        return todoRepository.delete(todoId)
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 할 일이 없습니다"));
    }

    public Todo updateTodo(int todoId, String description) {
        return todoRepository.update(todoId, description)
                .orElseThrow(() -> new NoSuchElementException("해당 ID의 할 일이 없습니다"));
    }

    public List<Todo> getTodoList() {
        return todoRepository.getTodoList();
    }

    public List<Todo> searchTodoList(String keyword) {
        return todoRepository.getSearchTodoList(keyword);
    }
}

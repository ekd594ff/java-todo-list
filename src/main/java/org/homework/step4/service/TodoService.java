package org.homework.step4.service;

import org.homework.step4.entity.Todo;
import org.homework.step4.dto.CreateTodoDTO;
import org.homework.step4.jdbc.JDBCConnectionPool;
import org.homework.step4.repository.MapTodoRepository;
import org.homework.step4.repository.MySQLTodoRepository;
import org.homework.step4.repository.TodoRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

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

    public void clear() {
        this.todoRepository.clearConnections();
    }
}

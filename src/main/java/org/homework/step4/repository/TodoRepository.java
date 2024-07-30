package org.homework.step4.repository;

import org.homework.step4.entity.Todo;
import org.homework.step4.dto.CreateTodoDTO;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {
    Optional<Todo> select(int id);
    Todo insert(CreateTodoDTO createTodoDTO);
    Optional<Todo> delete(int id);
    Optional<Todo> update(int id, String description);
    List<Todo> getTodoList();
    List<Todo> getSearchTodoList(String keyword);
    void clearConnections();
}

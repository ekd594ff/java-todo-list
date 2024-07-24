package org.homework.step3.repository;

import org.homework.step3.Todo;
import org.homework.step3.dto.CreateTodoDTO;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class TodoRepository {
    static private final Map<Integer, Todo> map = new HashMap<>();

    private static int todoId = 0;

    private static int getTodoId() {
        return ++todoId;
    }

    public Optional<Todo> select(int id) {
        return Optional.ofNullable(map.get(id));
    }

    public Todo insert(CreateTodoDTO createTodoDTO) {
        Todo todo = new Todo(getTodoId(), createTodoDTO.getDescription(), createTodoDTO.getDeadline());
        map.put(todo.getId(), todo);
        return todo;
    }

    public Optional<Todo> delete(int id) {
        return Optional.ofNullable(map.remove(id));
    }

    public Optional<Todo> update(int id, String description) {
        return Optional.ofNullable(map.get(id))
                .map(todo -> {
                    todo.setDescription(description);
                    return todo;
                });
    }

    public List<Todo> getTodoList() {
        LocalDateTime now = LocalDateTime.now();
        return map.values()
                .stream()
                .filter(item -> Period.between(now.toLocalDate(), item.getDeadline().toLocalDate()).getDays() <= 7)
                .sorted(Comparator.comparing(Todo::getDeadline))
                .collect(Collectors.toList());
    }

    public List<Todo> getSearchTodoList(String keyword) {
        return map
                .values()
                .stream()
                .filter(item -> item.getDescription().contains(keyword))
                .sorted(Comparator.comparing(Todo::getDeadline))
                .collect(Collectors.toList());
    }
}

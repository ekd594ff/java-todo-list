package org.homework.step2.repository;

import org.homework.step2.Todo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class TodoRepository {
    static private final Map<Integer, Todo> map = new HashMap<>();

    private static int todoId = 0;

    private static int getTodoId() {
        return todoId++;
    }

    public Todo select(int id) {
        return map.get(id);
    }

    public Todo insert(String description) {
        Todo todo = new Todo(description, getTodoId());
        map.put(todo.getId(), todo);
        return todo;
    }

    public Todo delete(int id) {
        return map.remove(id);
    }

    public Todo update(int id, String description) {
        return Optional.ofNullable(map.get(id))
                .map(todo -> {
                    todo.setDescription(description);
                    return todo;
                })
                .orElse(null);
    }
}

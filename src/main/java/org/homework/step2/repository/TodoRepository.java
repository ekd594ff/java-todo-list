package org.homework.step2.repository;

import org.homework.step2.Todo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class TodoRepository {
    static private final Map<Integer, Todo> map = new HashMap<>();

    public Todo select(int id) {
        return map.get(id);
    }

    public Todo insert(String description) {
        Todo todo = new Todo(description);
        map.put(todo.getId(), todo);
        return todo;
    }

    public Todo delete(int id) {
        return map.remove(id);
    }

    public String update(int id, String description) {
        return Optional.ofNullable(map.get(id))
                .map(todo -> "할 일 ID: " + todo.getId() + " 내용: " + todo.setDescription(description))
                .orElse("해당 ID의 할 일이 없습니다");
    }
}

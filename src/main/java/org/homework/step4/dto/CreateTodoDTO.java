package org.homework.step4.dto;

import org.homework.step4.entity.Todo;

import java.time.LocalDateTime;

public class CreateTodoDTO {
    private final String description;
    private final LocalDateTime deadline;

    public CreateTodoDTO(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public Todo toTodo() {
        return new Todo(0, this.description, this.deadline);
    }
}

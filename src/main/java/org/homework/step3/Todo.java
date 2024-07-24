package org.homework.step3;

import org.homework.step3.enums.Status;

import java.time.LocalDateTime;

public class Todo {
    private int id;
    private String description;
    private Status status;

    private LocalDateTime deadline;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public String setDescription(String description) {
        this.description = description;
        return this.description;
    }

    public Todo(int todoId, String description, LocalDateTime deadline) {
        this.id = todoId;
        this.description = description;
        this.status = Status.INCOMPLETE;
        this.deadline = deadline;
    }
}

package org.homework.step4.entity;

import org.homework.step4.enums.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Todo(int todoId, String description, Status status, LocalDateTime deadline) {
        this.id = todoId;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
    }

}

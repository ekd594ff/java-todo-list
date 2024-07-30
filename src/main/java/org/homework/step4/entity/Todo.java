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

    public static Todo toTodo(ResultSet resultSet) {
        int index = 1;
        int id = 0;
        String description = "";
        Status status = Status.valueOf("INCOMPLETE");
        LocalDateTime deadline = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            do {
                if (!resultSet.next()) {
                    return null;
                }
                id = resultSet.getInt(index++);
                description = resultSet.getString(index++);
                status = Status.of(resultSet.getString(index++));
                deadline = LocalDateTime.parse(resultSet.getString(index++), dateTimeFormatter);
                index = 1;
            } while (resultSet.next());
            return new Todo(id, description, status, deadline);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Todo toTodoItem(ResultSet resultSet) {
        int index = 1;
        int id = 0;
        String description = "";
        Status status = Status.valueOf("INCOMPLETE");
        LocalDateTime deadline = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            id = resultSet.getInt(index++);
            description = resultSet.getString(index++);
            status = Status.of(resultSet.getString(index++));
            deadline = LocalDateTime.parse(resultSet.getString(index++), dateTimeFormatter);
            return new Todo(id, description, status, deadline);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

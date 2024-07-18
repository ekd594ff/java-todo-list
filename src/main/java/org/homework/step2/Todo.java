package org.homework.step2;

import org.homework.step2.enums.Status;

public class Todo {
    private int id;
    private String description;
    private Status status;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public String setDescription(String description) {
        this.description = description;
        return this.description;
    }

    public Todo(String description, int todoId) {
        this.id = todoId;
        this.description = description;
        this.status = Status.INCOMPLETE;
    }
}

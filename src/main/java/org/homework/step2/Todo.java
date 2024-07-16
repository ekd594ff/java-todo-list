package org.homework.step2;

import org.homework.step2.enums.Status;

public class Todo {
    private static int num = 0;
    private int id;
    private String description;
    private Status status;

    private void incrementNum() {
        ++num;
    }

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

    public Todo(String description) {
        this.incrementNum();
        this.id = num;
        this.description = description;
        this.status = Status.INCOMPLETE;
    }
}

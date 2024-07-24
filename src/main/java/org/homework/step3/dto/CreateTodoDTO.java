package org.homework.step3.dto;

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
}

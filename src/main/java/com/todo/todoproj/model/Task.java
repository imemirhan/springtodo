package com.todo.todoproj.model;

import org.jline.utils.Log;

import java.io.Console;

public class Task {
    private Long id;
    private String description;
    private TaskStatusEnum status;

    public Task(Long id, String description) {
        this.id = id;
        this.description = description;
        this.status = TaskStatusEnum.Created;
    }

    // Getters and setters
    public Long getId() { return id; }
    public String getDescription() { return description; }
    public TaskStatusEnum getStatus() { return status; }
    public boolean changeStatus(TaskStatusEnum status) {
        try {
            this.status = status;
        } catch(Exception ex) {
            Log.error("An error occured changing the status of the task.", ex.getMessage());
            return false;
        }
        return true;
    }

    public void setCompleted() {
        this.status = TaskStatusEnum.Completed;
    }
}

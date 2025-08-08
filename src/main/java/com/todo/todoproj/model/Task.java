package com.todo.todoproj.model;

import org.jline.utils.Log;

public class Task {
    private Long id;
    private String description;
    private TaskStatusEnum status;

    public Task() {
    }

    public Task(Long id, String description) {
        this.id = id;
        this.description = description;
        this.status = TaskStatusEnum.Created;
    }

    public Long getId() { return id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatusEnum getStatus() { return status; }
    public void setStatus(TaskStatusEnum status) { this.status = status; }

    public boolean changeStatus(TaskStatusEnum status) {
        try {
            this.status = status;
        } catch (Exception ex) {
            Log.error("An error occurred changing the status of the task.", ex.getMessage());
            return false;
        }
        return true;
    }

    public void setCompleted() {
        this.status = TaskStatusEnum.Completed;
    }
}

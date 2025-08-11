package com.todo.todoproj.model;

import org.jline.utils.Log;

import java.util.Date;

public class Task {
    private Long id;
    private String description;
    private TaskStatusEnum status;
    private Date createdAt;
    private Date updatedAt;

    public Task() {
    }

    public Task(Long id, String description) {
        this.id = id;
        this.description = description;
        this.status = TaskStatusEnum.Created;
        this.createdAt = new Date();
        this.updatedAt = null;
    }

    public Long getId() { return id; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public String getDescription() { return description; }

    public TaskStatusEnum getStatus() { return status; }

    public boolean changeStatus(TaskStatusEnum status) {
        try {
            this.status = status;
            this.updatedAt = new Date();
        } catch (Exception ex) {
            Log.error("An error occurred changing the status of the task.", ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean changeDescription(String description) {
        try {
            this.description = description;
            this.updatedAt = new Date();
        } catch (Exception ex) {
            Log.error("An error occurred changing the description of the task.", ex.getMessage());
            return false;
        }
        return true;
    }

    public void setCompleted() {
        this.status = TaskStatusEnum.Completed;
        this.updatedAt = new Date();
    }
}

package com.todo.todoproj.service;

import com.todo.todoproj.model.Task;
import com.todo.todoproj.model.TaskStatusEnum;
import org.jline.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    public Task addTask(String description) {
        Task task = new Task(nextId++, description);
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getAllDoneTasks() {
        return tasks.stream().filter(t -> t.getStatus() == TaskStatusEnum.Completed).toList();
    }

    public List<Task> getUndoneTasks() {
        return tasks.stream().filter(t -> t.getStatus() != TaskStatusEnum.Completed).toList();
    }

    public List<Task> getInProgressTasks() {
        return tasks.stream().filter(t -> t.getStatus() == TaskStatusEnum.InProgress).toList();
    }

    public boolean markAsDone(Long id) {
        Optional<Task> task = tasks.stream().filter(t -> t.getId().equals(id)).findFirst();
        task.ifPresent(Task::setCompleted);
        return task.isPresent();
    }

    public boolean updateTaskStatus(Long id, TaskStatusEnum status) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if (task.isPresent()) {
            boolean success = task.get().changeStatus(status);
            if (success) {
                Log.info("Task successfully marked as " + status + ".");
                return true;
            }
        } else {
            Log.error("No task found with id " + id);
            return false;
        }
        return false;
    }

    public boolean markAsInProgress(Long id) {
        return updateTaskStatus(id, TaskStatusEnum.InProgress);
    }

    public boolean markAsCompleted(Long id) {
        return updateTaskStatus(id, TaskStatusEnum.Completed);
    }


    public boolean deleteTask(Long id) {
        return tasks.removeIf(t -> t.getId().equals(id));
    }
}

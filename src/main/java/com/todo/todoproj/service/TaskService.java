package com.todo.todoproj.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.todoproj.model.Task;
import com.todo.todoproj.model.TaskStatusEnum;
import org.jline.utils.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File storageFile = new File("tasks.json");

    public TaskService() {
        loadTasks();
    }

    public Task addTask(String description) {
        Task task = new Task(nextId++, description);
        tasks.add(task);
        saveTasks();
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

    public boolean updateTaskStatus(Long id, TaskStatusEnum status) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if (task.isPresent()) {
            boolean success = task.get().changeStatus(status);
            if (success) {
                Log.info("Task successfully marked as " + status + ".");
                saveTasks();
                return true;
            }
        } else {
            Log.error("No task found with id " + id);
        }
        return false;
    }

    public boolean updateTask(long id, String description) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if(task.isPresent()) {
            boolean success = task.get().changeDescription(description);
            if (success) {
                Log.info("Task successfully updated");
                saveTasks();
                return true;
            }
        } else {
            Log.error("No task found with id " + id);
        }
        return false;
    }

    public boolean deleteTask(Long id) {
        boolean removed = tasks.removeIf(t -> t.getId().equals(id));
        if (removed) saveTasks();
        return removed;
    }

    /** Save tasks to JSON file */
    private void saveTasks() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(storageFile, tasks);
        } catch (IOException e) {
            Log.error("Error saving tasks: " + e.getMessage());
        }
    }

    /** Load tasks from JSON file */
    private void loadTasks() {
        if (storageFile.exists()) {
            try {
                List<Task> loaded = objectMapper.readValue(storageFile, new TypeReference<>() {});
                tasks.clear();
                tasks.addAll(loaded);

                // Keep IDs consistent
                nextId = tasks.stream()
                        .mapToLong(Task::getId)
                        .max()
                        .orElse(0) + 1;

            } catch (IOException e) {
                Log.error("Error loading tasks: " + e.getMessage());
            }
        }
    }
}

package com.todo.todoproj.cli;

import com.todo.todoproj.model.TaskStatusEnum;
import com.todo.todoproj.service.TaskService;
import com.todo.todoproj.model.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLineApp implements CommandLineRunner {

    private final TaskService taskService = new TaskService();

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("📝 Welcome to the ToDo CLI App!");

        while (true) {
            System.out.println("\nCommands: [add <desc>] [list] [donelist] [undonelist] [inprogresslist] [done <id>] [progress <id>] [delete <id>] [exit]");
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String[] command = input.split(" ", 2);

            switch (command[0]) {
                case "add":
                    if (command.length < 2 || command[1].trim().isEmpty()) {
                        System.out.println("⚠️ Please provide a task description.");
                    } else {
                        Task newTask = taskService.addTask(command[1].trim());
                        System.out.println("✅ Added: " + newTask.getId() + " - " + newTask.getDescription());
                    }
                    break;

                case "update":
                    if (command.length < 2 || command[1].trim().isEmpty()) {
                        System.out.println("⚠️ Please provide the task ID and new description in quotes.");
                    } else {
                        String inputs = command[1].trim();
                        // Regex to match two quoted parts
                        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\"([^\"]*)\"\\s+\"([^\"]*)\"");
                        java.util.regex.Matcher matcher = pattern.matcher(inputs);

                        if (matcher.matches()) {
                            try {
                                Long id = Long.parseLong(matcher.group(1));
                                String newDescription = matcher.group(2);

                                boolean updated = taskService.updateTask(id, newDescription);
                                System.out.println(updated ? "✅ Task updated." : "❌ Task not found.");
                            } catch (NumberFormatException e) {
                                System.out.println("⚠️ Invalid task ID.");
                            }
                        } else {
                            System.out.println("⚠️ Invalid format. Use: update \"id\" \"new description\"");
                        }
                    }
                    break;

                case "list":
                    System.out.println("\n📋 All Tasks:");
                    for (Task task : taskService.getAllTasks()) {
                        System.out.printf("[%s] %d: %s%n",
                                task.getStatus() == TaskStatusEnum.Completed ? "✔"
                                        : task.getStatus() == TaskStatusEnum.InProgress ? "⏳"
                                        : " ",
                                task.getId(),
                                task.getDescription());
                    }
                    break;

                case "donelist":
                    System.out.println("\n✔ Completed Tasks:");
                    for (Task task : taskService.getAllDoneTasks()) {
                        System.out.printf("[✔] %d: %s%n", task.getId(), task.getDescription());
                    }
                    break;

                case "undonelist":
                    System.out.println("\n📋 Undone Tasks:");
                    for (Task task : taskService.getUndoneTasks()) {
                        System.out.printf("[%s] %d: %s%n",
                                task.getStatus() == TaskStatusEnum.InProgress ? "⏳" : " ",
                                task.getId(),
                                task.getDescription());
                    }
                    break;

                case "inprogresslist":
                    System.out.println("\n⏳ In Progress Tasks:");
                    for (Task task : taskService.getInProgressTasks()) {
                        System.out.printf("[⏳] %d: %s%n", task.getId(), task.getDescription());
                    }
                    break;

                case "done":
                    handleStatusChange(command, TaskStatusEnum.Completed);
                    break;

                case "progress":
                    handleStatusChange(command, TaskStatusEnum.InProgress);
                    break;

                case "delete":
                    if (command.length < 2) {
                        System.out.println("⚠️ Provide the ID of the task to delete.");
                    } else {
                        try {
                            Long id = Long.parseLong(command[1]);
                            boolean deleted = taskService.deleteTask(id);
                            System.out.println(deleted ? "🗑️ Deleted." : "❌ Task not found.");
                        } catch (NumberFormatException e) {
                            System.out.println("⚠️ Invalid task ID.");
                        }
                    }
                    break;

                case "exit":
                    System.out.println("👋 Goodbye!");
                    return;

                default:
                    System.out.println("❓ Unknown command.");
            }
        }
    }

    private void handleStatusChange(String[] command, TaskStatusEnum status) {
        if (command.length < 2) {
            System.out.printf("⚠️ Provide the ID of the task to mark as %s.%n", status);
        } else {
            try {
                Long id = Long.parseLong(command[1]);
                boolean updated = taskService.updateTaskStatus(id, status);
                System.out.println(updated ? "✅ Status updated." : "❌ Task not found or update failed.");
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid task ID.");
            }
        }
    }
}

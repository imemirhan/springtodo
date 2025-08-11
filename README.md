A simple command-line ToDo application written in Java, featuring task management with persistent JSON storage and timestamp tracking.

---

## URL
Project URL: https://github.com/imemirhan/springtodo

---

## Features

- Add, list, update, delete tasks
- Manage task statuses: Created, InProgress, Completed
- Persistent task storage in a JSON file (`tasks.json`)
- Human-readable ISO-8601 date formatting for task creation and update timestamps
- Simple, intuitive CLI commands

---

## Getting Started

### Prerequisites

- Java 11 or higher
- Gradle build tool
- Internet connection to download dependencies (Jackson JSON library)

### Installation

1. Clone the repository:

   ```bash
   git clone https://your.repo.url.git
   cd your-project-folder
    ```
Build the project and download dependencies:
   ```bash
    ./gradlew build
  ```
    Running the Application
Run the main application:

   ```bash
  ./gradlew run
   ```
You will see the command prompt:
   ```bash
  📝 Welcome to the ToDo CLI App!
  
  Commands: [add <desc>] [list] [donelist] [undonelist] [inprogresslist] [done <id>] [progress <id>] [update "id" "new description"] [delete <id>] [exit]
  >
   ```
## Commands

- **add `<description>`**  
  Add a new task with the given description.

- **list**  
  List all tasks with status icons indicating their state.

- **donelist**  
  List all completed tasks.

- **undonelist**  
  List tasks that are not yet completed.

- **inprogresslist**  
  List tasks currently in progress.

- **done `<id>`**  
  Mark the task with the specified ID as completed.

- **progress `<id>`**  
  Mark the task with the specified ID as in progress.

- **update `"id" "new description"`**  
  Update the description of a task by ID (both parameters must be enclosed in quotes).

- **delete `<id>`**  
  Delete the task with the specified ID.

- **exit**  
  Exit the application.

---

## Data Persistence

- Tasks are saved to a `tasks.json` file in the project root directory after each change.
- Dates (`createdAt` and `updatedAt`) are stored as human-readable ISO-8601 formatted strings.
- On startup, tasks are loaded from the JSON file if it exists.

---

## Dependencies

- **Jackson Databind** — for JSON serialization and deserialization  
---

## Code Highlights

- The `Task` class contains fields for `id`, `description`, `status`, `createdAt`, and `updatedAt`.
- `TaskService` handles all task operations and manages JSON file reading/writing with proper date formatting.
- `CommandLineApp` provides the interactive command-line interface and parses user commands.


A simple command-line ToDo application written in Java, featuring task management with persistent JSON storage and timestamp tracking.

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
Commands
add <description>: Add a new task

list: List all tasks with status icons

donelist: List completed tasks

undonelist: List tasks not yet completed

inprogresslist: List tasks in progress

done <id>: Mark a task as completed

progress <id>: Mark a task as in progress

update "id" "new description": Update the description of a task by ID (both parameters in quotes)

delete <id>: Delete a task by ID

exit: Exit the application

Data Persistence
Tasks are saved to tasks.json in the project root after each change.

Dates (createdAt and updatedAt) are stored as human-readable ISO-8601 strings.

On startup, tasks are loaded from the JSON file if it exists.

Dependencies
Jackson Databind — for JSON serialization and deserialization

JLine — for CLI logging (optional)

Code Highlights
Task class includes fields for id, description, status, createdAt, and updatedAt.

TaskService handles task operations and JSON file read/write with date formatting configured.

CommandLineApp provides the interactive CLI and parses commands.

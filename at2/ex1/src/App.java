import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to the Enhanced Task Manager!");

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addTask();
                    break;
                case "2":
                    listTasks();
                    break;
                case "3":
                    completeTask();
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you for using Task Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== TASK MANAGER MENU =====");
        System.out.println("1. Add a new task");
        System.out.println("2. List all tasks (by deadline)");
        System.out.println("3. Mark a task as completed");
        System.out.println("4. Exit");
        System.out.println("============================");
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        LocalDate deadline = null;
        while (deadline == null) {
            System.out.print("Enter deadline (YYYY-MM-DD): ");
            String deadlineInput = scanner.nextLine();

            try {
                deadline = LocalDate.parse(deadlineInput, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format.");
            }
        }

        taskManager.addTask(description, deadline);
        System.out.println("Task added successfully!");
    }

    private static void listTasks() {
        System.out.println("\n===== TASKS (Ordered by Deadline) =====");
        List<Task> tasks = taskManager.listTasksByDeadline();

        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void completeTask() {
        listTasks();

        if (taskManager.listTasksByDeadline().isEmpty()) {
            return;
        }

        System.out.print("Enter the ID of the task to mark as completed: ");
        String id = scanner.nextLine();

        boolean result = taskManager.markTaskAsCompleted(id);
        if (result) {
            System.out.println("Task marked as completed successfully!");
        } else {
            System.out.println("Task not found. Please check the ID and try again.");
        }
    }
}
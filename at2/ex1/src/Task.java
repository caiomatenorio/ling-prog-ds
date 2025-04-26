import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Task {
    private String id;
    private String description;
    private LocalDate deadline;
    private boolean completed;

    public Task(String description, LocalDate deadline) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.deadline = deadline;
        this.completed = false;
    }

    public Task(String id, String description, LocalDate deadline, boolean completed) {
        this.id = id;
        this.description = description;
        this.deadline = deadline;
        this.completed = completed;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private String formatDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return deadline.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Due: %s) %s",
                id.substring(0, 8),
                description,
                formatDate(),
                completed ? "[COMPLETED]" : "");
    }

    // Convert task to JSON string
    public String toJson() {
        return String.format("{\"id\":\"%s\",\"description\":\"%s\",\"deadline\":\"%s\",\"completed\":%b}",
                id, description, formatDate(), completed);
    }
}
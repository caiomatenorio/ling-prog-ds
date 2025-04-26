
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private static final String DATA_FILE = "tasks.json";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TaskManager() {
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(String description, LocalDate deadline) {
        Task task = new Task(description, deadline);
        tasks.add(task);
        saveTasks();
    }

    public List<Task> listTasksByDeadline() {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        Collections.sort(sortedTasks, Comparator.comparing(Task::getDeadline));
        return sortedTasks;
    }

    public boolean markTaskAsCompleted(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id) || task.getId().startsWith(id)) {
                task.setCompleted(true);
                saveTasks();
                return true;
            }
        }
        return false;
    }

    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            writer.write("[");
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toJson());
                if (i < tasks.size() - 1) {
                    writer.write(",");
                }
            }
            writer.write("]");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasks() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }

        tasks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            String content = jsonContent.toString().trim();
            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
                if (!content.isEmpty()) {
                    String[] taskJsons = content.split("\\},\\{");
                    for (int i = 0; i < taskJsons.length; i++) {
                        String taskJson = taskJsons[i];
                        if (i == 0 && !taskJson.startsWith("{")) {
                            taskJson = "{" + taskJson;
                        }
                        if (i == taskJsons.length - 1 && !taskJson.endsWith("}")) {
                            taskJson = taskJson + "}";
                        }
                        tasks.add(parseTaskFromJson(taskJson));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }

    private Task parseTaskFromJson(String json) {
        String id = extractJsonField(json, "id");
        String description = extractJsonField(json, "description");
        String deadlineStr = extractJsonField(json, "deadline");
        LocalDate deadline = LocalDate.parse(deadlineStr, DATE_FORMATTER);
        boolean completed = Boolean.parseBoolean(extractJsonField(json, "completed"));

        return new Task(id, description, deadline, completed);
    }

    private String extractJsonField(String json, String field) {
        String searchPattern = "\"" + field + "\":";
        int start = json.indexOf(searchPattern) + searchPattern.length();
        int end;

        if (json.charAt(start) == '\"') {
            start++;
            end = json.indexOf("\"", start);
            return json.substring(start, end);
        } else {
            end = json.indexOf(",", start);
            if (end == -1) {
                end = json.indexOf("}", start);
            }
            return json.substring(start, end);
        }
    }
}
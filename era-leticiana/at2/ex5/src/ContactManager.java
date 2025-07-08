import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private List<Contact> contacts;
    private static final String DATA_FILE = "contacts.json";

    public ContactManager() {
        this.contacts = new ArrayList<>();
        loadContacts();
    }

    public void addContact(String name, String phoneNumber, String email) {
        Contact contact = new Contact(name, phoneNumber, email);
        contacts.add(contact);
        saveContacts();
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    public List<Contact> searchByName(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return new ArrayList<>();
        }

        List<Contact> results = new ArrayList<>();
        String lowerCaseSearchTerm = searchTerm.toLowerCase();

        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().contains(lowerCaseSearchTerm)) {
                results.add(contact);
            }
        }

        return results;
    }

    public void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            writer.write("[");
            for (int i = 0; i < contacts.size(); i++) {
                writer.write(contacts.get(i).toJson());
                if (i < contacts.size() - 1) {
                    writer.write(",");
                }
            }
            writer.write("]");
        } catch (IOException e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }

    public void loadContacts() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }

        contacts.clear();
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
                    List<String> jsonObjects = splitJsonArray(content);

                    for (String jsonObject : jsonObjects) {
                        Contact contact = parseContactFromJson(jsonObject);
                        if (contact != null) {
                            contacts.add(contact);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading contacts: " + e.getMessage());
        }
    }

    private List<String> splitJsonArray(String jsonArray) {
        List<String> results = new ArrayList<>();
        int start = 0;
        int braceCount = 0;
        boolean inQuotes = false;
        char[] chars = jsonArray.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '"' && (i == 0 || chars[i - 1] != '\\')) {
                inQuotes = !inQuotes;
            }

            if (!inQuotes) {
                if (c == '{') {
                    if (braceCount == 0) {
                        start = i;
                    }
                    braceCount++;
                } else if (c == '}') {
                    braceCount--;
                    if (braceCount == 0) {
                        results.add(jsonArray.substring(start, i + 1));
                    }
                }
            }
        }

        return results;
    }

    private Contact parseContactFromJson(String json) {
        String name = extractJsonField(json, "name");
        String phoneNumber = extractJsonField(json, "phoneNumber");
        String email = extractJsonField(json, "email");

        if (name != null) {
            return new Contact(name, phoneNumber, email);
        }
        return null;
    }

    private String extractJsonField(String json, String field) {
        String searchPattern = "\"" + field + "\":\"";
        int start = json.indexOf(searchPattern);

        if (start == -1) {
            return null;
        }

        start += searchPattern.length();
        int end = start;

        boolean foundEnd = false;
        while (!foundEnd && end < json.length()) {
            if (json.charAt(end) == '"' && json.charAt(end - 1) != '\\') {
                foundEnd = true;
            } else {
                end++;
            }
        }

        if (!foundEnd) {
            return null;
        }

        String result = json.substring(start, end);

        return result.replace("\\\"", "\"")
                .replace("\\\\", "\\")
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t");
    }
}
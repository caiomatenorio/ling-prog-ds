import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Product> products;
    private static final String DATA_FILE = "inventory.json";

    public InventoryManager() {
        this.products = new ArrayList<>();
        loadInventory();
    }

    public void addProduct(String name, int quantity, double price) {
        products.add(new Product(name, quantity, price));
        saveInventory();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public double getTotalInventoryValue() {
        double total = 0;
        for (Product product : products) {
            total += product.getTotalValue();
        }
        return total;
    }

    public void saveInventory() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            writer.write("[");
            for (int i = 0; i < products.size(); i++) {
                writer.write(products.get(i).toJson());
                if (i < products.size() - 1) {
                    writer.write(",");
                }
            }
            writer.write("]");
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void loadInventory() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return;
        }

        products.clear();
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
                    String[] productJsons = content.split("\\},\\{");
                    for (int i = 0; i < productJsons.length; i++) {
                        String productJson = productJsons[i];
                        if (i == 0 && !productJson.startsWith("{")) {
                            productJson = "{" + productJson;
                        }
                        if (i == productJsons.length - 1 && !productJson.endsWith("}")) {
                            productJson = productJson + "}";
                        }
                        products.add(parseProductFromJson(productJson));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }

    private Product parseProductFromJson(String json) {
        String name = extractJsonField(json, "name");
        int quantity = Integer.parseInt(extractJsonField(json, "quantity"));
        double price = Double.parseDouble(extractJsonField(json, "price"));

        return new Product(name, quantity, price);
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
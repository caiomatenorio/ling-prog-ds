import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryManager inventoryManager = new InventoryManager();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to the Intelligent Inventory System!");

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    displayInventory();
                    break;
                case "3":
                    running = false;
                    System.out.println("Thank you for using the Inventory System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== INVENTORY SYSTEM MENU =====");
        System.out.println("1. Add a new product");
        System.out.println("2. Display all products");
        System.out.println("3. Exit");
        System.out.println("===============================");
    }

    private static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        int quantity = -1;
        while (quantity < 0) {
            try {
                System.out.print("Enter quantity: ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative. Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        double price = -1;
        while (price < 0) {
            try {
                System.out.print("Enter price: ");
                price = Double.parseDouble(scanner.nextLine());
                if (price < 0) {
                    System.out.println("Price cannot be negative. Please enter a valid price.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }

        inventoryManager.addProduct(name, quantity, price);
        System.out.println("Product added successfully!");
    }

    private static void displayInventory() {
        System.out.println("\n===== INVENTORY PRODUCTS =====");
        List<Product> products = inventoryManager.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products available in inventory.");
            return;
        }

        for (Product product : products) {
            System.out.println(product);
        }

        System.out.printf("\nTotal Inventory Value: $%.2f\n",
                inventoryManager.getTotalInventoryValue());
    }
}
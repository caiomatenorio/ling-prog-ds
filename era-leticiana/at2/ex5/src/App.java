import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ContactManager contactManager = new ContactManager();

    public static void main(String[] args) {
        System.out.println("Welcome to the Contact Manager System!");

        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addContact();
                    break;
                case "2":
                    searchContacts();
                    break;
                case "3":
                    listAllContacts();
                    break;
                case "4":
                    running = false;
                    System.out.println("Thank you for using Contact Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n===== CONTACT MANAGER MENU =====");
        System.out.println("1. Add a new contact");
        System.out.println("2. Search contacts by name");
        System.out.println("3. List all contacts");
        System.out.println("4. Exit");
        System.out.println("===============================");
    }

    private static void addContact() {
        System.out.println("\n===== ADD NEW CONTACT =====");

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty. Contact not added.");
            return;
        }

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        contactManager.addContact(name, phoneNumber, email);
        System.out.println("Contact added successfully!");
    }

    private static void searchContacts() {
        System.out.println("\n===== SEARCH CONTACTS =====");

        System.out.print("Enter name to search: ");
        String searchTerm = scanner.nextLine().trim();

        if (searchTerm.isEmpty()) {
            System.out.println("Search term cannot be empty.");
            return;
        }

        List<Contact> results = contactManager.searchByName(searchTerm);
        displaySearchResults(results, searchTerm);
    }

    private static void displaySearchResults(List<Contact> contacts, String searchTerm) {
        System.out.println("\nSearch results for \"" + searchTerm + "\":");

        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("Found " + contacts.size() + " contact(s):");
        displayContacts(contacts);
    }

    private static void listAllContacts() {
        System.out.println("\n===== ALL CONTACTS =====");

        List<Contact> allContacts = contactManager.getAllContacts();

        if (allContacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("Total contacts: " + allContacts.size());
        displayContacts(allContacts);
    }

    private static void displayContacts(List<Contact> contacts) {
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println("\nContact #" + (i + 1) + ":");
            System.out.println(contacts.get(i));
            System.out.println("--------------------");
        }
    }
}
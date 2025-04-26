import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Bank bank = new Bank();

    public static void main(String[] args) {
        System.out.println("Welcome to the Simple Banking System!");

        boolean running = true;
        while (running) {
            if (bank.getCurrentUser() == null) {
                displayLoginMenu();
            } else {
                displayBankingMenu();
            }

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            if (bank.getCurrentUser() == null) {
                running = handleLoginChoice(choice);
            } else {
                running = handleBankingChoice(choice);
            }
        }

        System.out.println("Thank you for using our Banking System. Goodbye!");
        scanner.close();
    }

    private static void displayLoginMenu() {
        System.out.println("\n===== BANKING SYSTEM =====");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.println("=========================");
    }

    private static void displayBankingMenu() {
        System.out.println("\n===== BANKING MENU =====");
        System.out.println("Welcome, " + bank.getCurrentUser().getFullName() + "!");
        System.out.println("1. View My Accounts");
        System.out.println("2. Create New Account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Transaction History");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
        System.out.println("=======================");
    }

    private static boolean handleLoginChoice(String choice) {
        switch (choice) {
            case "1":
                login();
                return true;
            case "2":
                register();
                return true;
            case "3":
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
                return true;
        }
    }

    private static boolean handleBankingChoice(String choice) {
        switch (choice) {
            case "1":
                viewAccounts();
                return true;
            case "2":
                createAccount();
                return true;
            case "3":
                deposit();
                return true;
            case "4":
                withdraw();
                return true;
            case "5":
                viewTransactionHistory();
                return true;
            case "6":
                logout();
                return true;
            case "7":
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
                return true;
        }
    }

    private static void login() {
        System.out.println("\n===== LOGIN =====");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        boolean success = bank.login(username, password);
        if (success) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void register() {
        System.out.println("\n===== REGISTER =====");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();

        boolean success = bank.registerUser(username, password, fullName);
        if (success) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    private static void logout() {
        bank.logout();
        System.out.println("You have been logged out.");
    }

    private static void viewAccounts() {
        List<Account> accounts = bank.getCurrentUserAccounts();

        System.out.println("\n===== YOUR ACCOUNTS =====");
        if (accounts.isEmpty()) {
            System.out.println("You don't have any accounts yet. Create one first.");
            return;
        }

        for (Account account : accounts) {
            System.out.printf("Account: %s | Balance: $%.2f\n",
                    account.getAccountNumber(), account.getBalance());
        }
    }

    private static void createAccount() {
        System.out.println("\n===== CREATE NEW ACCOUNT =====");

        String accountNumber = bank.createAccount();
        if (accountNumber != null) {
            System.out.println("Account created successfully!");
            System.out.println("Your new account number is: " + accountNumber);
        } else {
            System.out.println("Account creation failed.");
        }
    }

    private static void deposit() {
        System.out.println("\n===== DEPOSIT =====");

        List<Account> accounts = bank.getCurrentUserAccounts();
        if (accounts.isEmpty()) {
            System.out.println("You don't have any accounts yet. Create one first.");
            return;
        }

        displayAccountsList(accounts);
        Account account = selectAccount(accounts);

        if (account == null) {
            return;
        }

        double amount = getPositiveAmount("Enter amount to deposit: ");

        if (amount > 0) {
            boolean success = bank.deposit(account.getAccountNumber(), amount);
            if (success) {
                System.out.printf("Deposit successful. New balance: $%.2f\n", account.getBalance());
            } else {
                System.out.println("Deposit failed.");
            }
        }
    }

    private static void withdraw() {
        System.out.println("\n===== WITHDRAW =====");

        List<Account> accounts = bank.getCurrentUserAccounts();
        if (accounts.isEmpty()) {
            System.out.println("You don't have any accounts yet. Create one first.");
            return;
        }

        displayAccountsList(accounts);
        Account account = selectAccount(accounts);

        if (account == null) {
            return;
        }

        double amount = getPositiveAmount("Enter amount to withdraw: ");

        if (amount > 0) {
            if (amount > account.getBalance()) {
                System.out.println("Insufficient funds.");
                return;
            }

            boolean success = bank.withdraw(account.getAccountNumber(), amount);
            if (success) {
                System.out.printf("Withdrawal successful. New balance: $%.2f\n", account.getBalance());
            } else {
                System.out.println("Withdrawal failed.");
            }
        }
    }

    private static void viewTransactionHistory() {
        System.out.println("\n===== TRANSACTION HISTORY =====");

        List<Account> accounts = bank.getCurrentUserAccounts();
        if (accounts.isEmpty()) {
            System.out.println("You don't have any accounts yet.");
            return;
        }

        displayAccountsList(accounts);
        Account account = selectAccount(accounts);

        if (account == null) {
            return;
        }

        List<Transaction> transactions = bank.getAccountTransactions(account.getAccountNumber());

        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this account.");
            return;
        }

        System.out.printf("\nAccount: %s | Current Balance: $%.2f\n",
                account.getAccountNumber(), account.getBalance());

        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void displayAccountsList(List<Account> accounts) {
        System.out.println("\nYour accounts:");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("%d. Account: %s | Balance: $%.2f\n",
                    i + 1, accounts.get(i).getAccountNumber(), accounts.get(i).getBalance());
        }
    }

    private static Account selectAccount(List<Account> accounts) {
        if (accounts.isEmpty()) {
            return null;
        }

        int selection = -1;
        while (selection < 1 || selection > accounts.size()) {
            System.out.print("Select an account (1-" + accounts.size() + "): ");
            try {
                selection = Integer.parseInt(scanner.nextLine());
                if (selection < 1 || selection > accounts.size()) {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        return accounts.get(selection - 1);
    }

    private static double getPositiveAmount(String prompt) {
        double amount = -1;
        while (amount <= 0) {
            System.out.print(prompt);
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    System.out.println("Amount must be greater than zero.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }
        return amount;
    }
}
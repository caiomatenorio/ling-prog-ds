import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Bank {
    private Map<String, User> users;
    private List<Account> accounts;
    private List<Transaction> allTransactions;
    private User currentUser;

    private static final String USERS_FILE = "users.txt";
    private static final String ACCOUNTS_FILE = "accounts.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";

    public Bank() {
        users = new HashMap<>();
        accounts = new ArrayList<>();
        allTransactions = new ArrayList<>();
        loadData();
    }

    public boolean registerUser(String username, String password, String fullName) {
        if (users.containsKey(username)) {
            return false;
        }

        User newUser = new User(username, password, fullName);
        users.put(username, newUser);
        saveUsers();
        return true;
    }

    public boolean login(String username, String password) {
        User user = users.get(username);

        if (user != null && user.checkPassword(password)) {
            currentUser = user;
            return true;
        }

        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String createAccount() {
        if (currentUser == null) {
            return null;
        }

        String accountNumber = generateAccountNumber();
        Account newAccount = new Account(accountNumber, currentUser);
        accounts.add(newAccount);
        saveAccounts();
        return accountNumber;
    }

    private String generateAccountNumber() {
        Random random = new Random();
        String accountNumber;

        do {
            accountNumber = String.format("%010d", random.nextInt(1000000000));
        } while (getAccount(accountNumber) != null);

        return accountNumber;
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> getCurrentUserAccounts() {
        if (currentUser == null) {
            return new ArrayList<>();
        }

        List<Account> userAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getOwner().getUsername().equals(currentUser.getUsername())) {
                userAccounts.add(account);
            }
        }
        return userAccounts;
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);

        if (account != null && currentUser != null &&
                account.getOwner().getUsername().equals(currentUser.getUsername())) {

            boolean success = account.deposit(amount);
            if (success) {
                Transaction transaction = account.getTransactions().get(account.getTransactions().size() - 1);
                allTransactions.add(transaction);
                saveTransactions();
                saveAccounts(); // Update account balance
            }
            return success;
        }

        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);

        if (account != null && currentUser != null &&
                account.getOwner().getUsername().equals(currentUser.getUsername())) {

            boolean success = account.withdraw(amount);
            if (success) {
                Transaction transaction = account.getTransactions().get(account.getTransactions().size() - 1);
                allTransactions.add(transaction);
                saveTransactions();
                saveAccounts(); // Update account balance
            }
            return success;
        }

        return false;
    }

    public List<Transaction> getAccountTransactions(String accountNumber) {
        Account account = getAccount(accountNumber);

        if (account != null && currentUser != null &&
                account.getOwner().getUsername().equals(currentUser.getUsername())) {

            return account.getTransactions();
        }

        return new ArrayList<>();
    }

    private void loadData() {
        loadUsers();
        loadAccounts();
        loadTransactions();
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users.values()) {
                writer.write(user.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    private void loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromFileString(line);
                if (user != null) {
                    users.put(user.getUsername(), user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }

    private void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNTS_FILE))) {
            for (Account account : accounts) {
                writer.write(account.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving accounts: " + e.getMessage());
        }
    }

    private void loadAccounts() {
        File file = new File(ACCOUNTS_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String accountNumber = parts[0];
                    String username = parts[1];
                    double balance = Double.parseDouble(parts[2]);

                    User owner = users.get(username);
                    if (owner != null) {
                        Account account = new Account(accountNumber, owner, balance);
                        accounts.add(account);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading accounts: " + e.getMessage());
        }
    }

    private void saveTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE))) {
            for (Transaction transaction : allTransactions) {
                writer.write(transaction.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving transactions: " + e.getMessage());
        }
    }

    private void loadTransactions() {
        File file = new File(TRANSACTIONS_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transaction transaction = Transaction.fromFileString(line);
                if (transaction != null) {
                    allTransactions.add(transaction);

                    // Associate transaction with its account
                    Account account = getAccount(transaction.getAccountNumber());
                    if (account != null) {
                        account.addTransactionFromFile(transaction);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        }
    }
}
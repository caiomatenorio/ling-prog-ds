import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private User owner;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountNumber, User owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public Account(String accountNumber, User owner, double initialBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public User getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    private Transaction addTransaction(Transaction.TransactionType type, double amount) {
        Transaction transaction = new Transaction(accountNumber, type, amount, balance);
        transactions.add(transaction);
        return transaction;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }

        balance += amount;
        addTransaction(Transaction.TransactionType.DEPOSIT, amount);
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        addTransaction(Transaction.TransactionType.WITHDRAWAL, amount);
        return true;
    }

    public void addTransactionFromFile(Transaction transaction) {
        if (transaction.getAccountNumber().equals(accountNumber)) {
            transactions.add(transaction);
        }
    }

    public String toFileString() {
        return String.format("%s,%s,%f", accountNumber, owner.getUsername(), balance);
    }

    public static Account fromFileString(String fileString, User owner) {
        String[] parts = fileString.split(",");
        if (parts.length == 3 && parts[1].equals(owner.getUsername())) {
            String accountNumber = parts[0];
            double balance = Double.parseDouble(parts[2]);
            return new Account(accountNumber, owner, balance);
        }
        return null;
    }
}
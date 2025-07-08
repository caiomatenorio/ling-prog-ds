import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String accountNumber;
    private LocalDateTime timestamp;
    private TransactionType type;
    private double amount;
    private double balanceAfterTransaction;

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }

    public Transaction(String accountNumber, TransactionType type, double amount, double balanceAfterTransaction) {
        this.accountNumber = accountNumber;
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public Transaction(String accountNumber, LocalDateTime timestamp, TransactionType type,
            double amount, double balanceAfterTransaction) {
        this.accountNumber = accountNumber;
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String typeStr = (type == TransactionType.DEPOSIT) ? "Deposit" : "Withdrawal";
        return String.format("%s | %s | $%.2f | Balance: $%.2f",
                timestamp.format(formatter), typeStr, amount, balanceAfterTransaction);
    }

    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s,%s,%s,%f,%f",
                accountNumber, timestamp.format(formatter), type.name(), amount, balanceAfterTransaction);
    }

    public static Transaction fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        if (parts.length == 5) {
            String accountNumber = parts[0];
            LocalDateTime timestamp = LocalDateTime.parse(parts[1],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            TransactionType type = TransactionType.valueOf(parts[2]);
            double amount = Double.parseDouble(parts[3]);
            double balance = Double.parseDouble(parts[4]);

            return new Transaction(accountNumber, timestamp, type, amount, balance);
        }
        return null;
    }
}
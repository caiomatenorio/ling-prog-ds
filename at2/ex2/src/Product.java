
public class Product {
    private String name;
    private int quantity;
    private double price;

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalValue() {
        return quantity * price;
    }

    public String toJson() {
        return String.format("{\"name\":\"%s\",\"quantity\":%d,\"price\":%.2f}",
                name, quantity, price);
    }

    @Override
    public String toString() {
        return String.format("Product: %s, Quantity: %d, Price: $%.2f, Total Value: $%.2f",
                name, quantity, price, getTotalValue());
    }
}
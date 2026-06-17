public class Product {
    private String id;
    private String name;
    private String category;
    private Double price; // Changed from double to Double to allow null values safely

    public Product(String id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getPrice() { return price; } // Returns Double object

    @Override
    public String toString() {
        String priceDisplay = (price == null) ? "MISSING" : "$" + price;
        return String.format("%-12s | Category: %-12s | Price: %s", name, category, priceDisplay);
    }
}
package week2.model;

public class LineItem {
    private String productName;
    private double price;
    private int quantity;
    private String category;
    private String productId;

    public LineItem(String productName, double price, int quantity, String category, String productId ){
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.productId = productId;
    }

    public String getProductName(){
        return productName;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getCategory(){
        return category;
    }
    public String getProductId(){
        return productId;
    }


}

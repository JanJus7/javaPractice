package lab3;

public class Product {
    private final String code;
    private final String name;
    private final double price;
    private double discountPrice;

    public Product(String code, String name, double price, double discountPrice) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getDiscountPrice() { return discountPrice; }
    public void setDiscountPrice( double discountPrice ) { this.discountPrice = discountPrice; }
}

package lab3;

import java.util.List;

public class TotalPriceDiscount implements Discount {
    private final double threshold;
    private final double discountPercent;

    public TotalPriceDiscount(double threshold, double discountPercent) {
        this.threshold = threshold;
        this.discountPercent = discountPercent;
    }

    @Override
    public void apply(List<Product> products) {
        double total = products.stream().mapToDouble(Product::getDiscountPrice).sum();
        if (total > threshold) {
            for (Product product : products) {
                double newPrice = product.getDiscountPrice() * (1 - discountPercent / 100);
                product.setDiscountPrice(newPrice);
            }
            System.out.println("Zastosowano " + discountPercent + "% rabatu na wszystkie produkty.");
        }
    }
}


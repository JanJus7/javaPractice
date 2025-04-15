package lab3;

import java.util.List;

public class SpecificProductDiscount implements Discount {
    private final String productCode;
    private final double discountPercent;

    public SpecificProductDiscount(String productCode, double discountPercent) {
        this.productCode = productCode;
        this.discountPercent = discountPercent;
    }

    @Override
    public void apply(List<Product> products) {
        for (Product product : products) {
            if (product.getCode().equals(productCode)) {
                double newPrice = product.getPrice() * (1 - discountPercent / 100);
                product.setDiscountPrice(newPrice);
                System.out.println("Zastosowano " + discountPercent + "% zniżki na produkt: " + product.getName());
            }
        }
    }
}
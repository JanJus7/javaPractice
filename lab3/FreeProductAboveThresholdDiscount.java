package lab3;

import java.util.List;

public class FreeProductAboveThresholdDiscount implements Discount {
    private final double threshold;
    private final Product freeProduct;

    public FreeProductAboveThresholdDiscount(double threshold, Product freeProduct) {
        this.threshold = threshold;
        this.freeProduct = freeProduct;
    }

    @Override
    public void apply(List<Product> products) {
        double total = products.stream().mapToDouble(Product::getPrice).sum();
        if (total > threshold) {
            Product free = new Product(freeProduct.getCode(), freeProduct.getName(), 
                                      freeProduct.getPrice(), 0);
            products.add(free);
            System.out.println("Dodano gratisowy produkt: " + free.getName());
        }
    }
}
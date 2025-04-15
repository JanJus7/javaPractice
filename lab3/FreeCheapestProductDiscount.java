package lab3;

import java.util.List;

public class FreeCheapestProductDiscount implements Discount {
    @Override
    public void apply(List<Product> products) {
        if (products.size() < 3) return;

        Product cheapest = products.get(0);
        for (Product product : products) {
            if (product.getPrice() < cheapest.getPrice()) {
                cheapest = product;
            }
        }
        cheapest.setDiscountPrice(0);
        System.out.println("Najtańszy produkt gratis: " + cheapest.getName());
    }
}


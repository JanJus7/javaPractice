package lab3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Methods {
    private final List<Product> products;
    private final List<Discount> automaticDiscounts;
    private final List<Discount> manualDiscounts;

    public Methods() {
        this.products = new ArrayList<>();
        this.automaticDiscounts = new ArrayList<>();
        this.manualDiscounts = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        applyAutomaticDiscounts();
    }

    public void addAutomaticDiscount(Discount discount) {
        automaticDiscounts.add(discount);
        applyAutomaticDiscounts();
    }

    public void addManualDiscount(Discount discount) {
        manualDiscounts.add(discount);
    }

    private void applyAutomaticDiscounts() {
        resetDiscountPrices();
        
        for (Discount discount : automaticDiscounts) {
            discount.apply(products);
        }
    }

    public void applyManualDiscounts() {
        for (Discount discount : manualDiscounts) {
            discount.apply(products);
        }
    }

    private void resetDiscountPrices() {
        for (Product product : products) {
            product.setDiscountPrice(product.getPrice());
        }
    }

    public void sortProducts() {
        products.sort(Comparator
                .comparingDouble(Product::getPrice)
                .reversed()
                .thenComparing(Product::getName)
        );

        System.out.println("Posortowane produkty:");
        for (Product product : products) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }
    }

    public Product getMostExpensiveProduct() {
        if (products.isEmpty()) return null;
        
        Product mostExpensive = products.get(0);
        for (Product product : products) {
            if (product.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = product;
            }
        }
        return mostExpensive;
    }

    public Product getCheapestProduct() {
        if (products.isEmpty()) return null;
        
        Product cheapest = products.get(0);
        for (Product product : products) {
            if (product.getPrice() < cheapest.getPrice()) {
                cheapest = product;
            }
        }
        return cheapest;
    }

    public List<Product> getNMostExpensiveProducts(int n) {
        if (n <= 0) return new ArrayList<>();
        if (n > products.size()) {
            System.out.println("Nie ma tylu produktów.");
            return new ArrayList<>();
        }

        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        
        return sorted.subList(0, n);
    }

    public List<Product> getNCheapestProducts(int n) {
        if (n <= 0) return new ArrayList<>();
        if (n > products.size()) {
            System.out.println("Nie ma tylu produktów.");
            return new ArrayList<>();
        }

        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparingDouble(Product::getPrice));
        
        return sorted.subList(0, n);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getDiscountPrice();
        }
        return total;
    }

    public void printProducts() {
        System.out.println("Produkty w koszyku:");
        for (Product product : products) {
            System.out.printf("%s (kod: %s) - Cena: %.2f, Cena po promocji: %.2f%n",
                    product.getName(),
                    product.getCode(),
                    product.getPrice(),
                    product.getDiscountPrice());
        }
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}
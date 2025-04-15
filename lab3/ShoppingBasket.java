package lab3;

public class ShoppingBasket {
    public static void main(String[] args) {
        Methods basket = new Methods();

        basket.addAutomaticDiscount(new FreeCheapestProductDiscount());
        basket.addAutomaticDiscount(new FreeProductAboveThresholdDiscount(25, 
            new Product("GIFT", "Darmowy prezent", 0, 0)));
        basket.addAutomaticDiscount(new TotalPriceDiscount(50, 5));

        basket.addManualDiscount(new SpecificProductDiscount("003", 15));

        System.out.println("--- Dodawanie produktów ---");
        basket.addProduct(new Product("001", "Chleb", 5.0, 5.0));
        basket.printProducts();
        System.out.println("Suma: " + basket.getTotalPrice() + "\n");

        basket.addProduct(new Product("002", "Mleko", 3.0, 3.0));
        basket.printProducts();
        System.out.println("Suma: " + basket.getTotalPrice() + "\n");

        basket.addProduct(new Product("003", "Ser", 10.0, 10.0));
        basket.printProducts();
        System.out.println("Suma: " + basket.getTotalPrice() + "\n");

        basket.addProduct(new Product("004", "Masło", 8.0, 8.0));
        basket.printProducts();
        System.out.println("Suma: " + basket.getTotalPrice() + "\n");

        System.out.println("--- Stosowanie ręcznych promocji ---");
        basket.applyManualDiscounts();
        basket.printProducts();
        System.out.println("Suma po ręcznych promocjach: " + basket.getTotalPrice() + "\n");

        System.out.println("--- Inne funkcjonalności ---");
        System.out.println("Najdroższy produkt: " + basket.getMostExpensiveProduct().getName());
        System.out.println("Najtańszy produkt: " + basket.getCheapestProduct().getName());

        System.out.println("\n3 najdroższe produkty:");
        basket.getNMostExpensiveProducts(3).forEach(p -> 
            System.out.println(p.getName() + " - " + p.getPrice()));

        System.out.println("\n2 najtańsze produkty:");
        basket.getNCheapestProducts(2).forEach(p -> 
            System.out.println(p.getName() + " - " + p.getPrice()));

        System.out.println("\nPosortowane produkty:");
        basket.sortProducts();
    }
}
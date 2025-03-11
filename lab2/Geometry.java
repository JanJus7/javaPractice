package lab2;

public class Geometry {

    public final String name;
    public double area;

    public Geometry(String name, double area) {
        this.name = name;
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public double modifyArea(double number) {
        return area = number;
    }

    public String getName() {
        return name;
    }

    
    
    public static void main(String[] args) {
        
        Rectangle rectangle = new Rectangle("Kwadrat 1", 0, 6, 6);
        rectangle.calculateArea();
        System.out.println(rectangle.getArea());
        System.out.println(rectangle.getName());

        Circle circle = new Circle("Koło 1", 0, 1);
        circle.calculateArea();
        System.out.println(circle.getArea());
        System.out.println(circle.getName());

        Triangle triangle = new Triangle("Trójkąt 1", 0, 4, 4);
        triangle.calculateArea();
        System.out.println(triangle.getArea());
        System.out.println(triangle.getName());
    }
}

class Rectangle extends Geometry {
    private final double width;
    private final double height;
    
    public Rectangle(String name, double area, double width, double height) {
        super(name, area);
        this.width = width;
        this.height = height;
    }

    public double calculateArea() {
        return area = width * height;
    }
}

class Circle extends Geometry {
    private final double radius;
    
    public Circle(String name, double area, double radius) {
        super(name, area);
        this.radius = radius;
    }

    public double calculateArea() {
        return area = Math.PI * radius * radius;
    }
}

class Triangle extends Geometry {
    private final double base;
    private final double height;
    
    public Triangle(String name, double area, double base, double height) {
        super(name, area);
        this.base = base;
        this.height = height;
    }

    public double calculateArea() {
        return area = 0.5 * base * height;
    }
}
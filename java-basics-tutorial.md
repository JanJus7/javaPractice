# Java Podstawy: składnia i inicjalizacja obiektów

## Składnia: Struktura programu

Każdy program zaczyna się definicą klasy 
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Struktura:
- `public class HelloWorld`: Deklaracja klasy
- `public static void main(String[] args)`: Metoda main (wykonywana przy uruchomieniu kodu)
- wyrażenia kończą się średnikami (`;`)
- Bloki kodu w nawiasach klamrowych `{}`

## Typy zmiennych i danych

Typy proste i referencyjne 

### Typy proste

```java
// Numeric types
int age = 25;                // Integer (32-bit)
long population = 7800000000L; // Long integer (64-bit)
double price = 19.99;        // Double precision floating point
float temperature = 98.6f;   // Single precision floating point

// Character type
char grade = 'A';            // Single character

// Boolean type
boolean isActive = true;     // true or false

// Byte and short
byte smallNumber = 127;      // 8-bit integer
short mediumNumber = 32000;  // 16-bit integer
```

### Typy referencyjne

```java
String name = "John";        // String object
int[] numbers = {1, 2, 3, 4}; // Array object
```

## Podstawowe operacje

```java
// Arithmetic operators
int sum = 5 + 3;             // Addition
int difference = 10 - 4;     // Subtraction
int product = 6 * 3;         // Multiplication
int quotient = 15 / 3;       // Division
int remainder = 7 % 3;       // Modulus (remainder)

// Comparison operators
boolean isEqual = (5 == 5);  // Equal to
boolean notEqual = (5 != 3); // Not equal to
boolean isGreater = (7 > 4); // Greater than
boolean isLess = (2 < 9);    // Less than

// Logical operators
boolean andResult = true && false; // Logical AND
boolean orResult = true || false;  // Logical OR
boolean notResult = !true;         // Logical NOT
```

## Instrukcje kontrolne

### Instrukcje warunkowe

```java
// If statement
if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}

// Switch statement
switch (grade) {
    case 'A':
        System.out.println("Excellent");
        break;
    case 'B':
        System.out.println("Good");
        break;
    default:
        System.out.println("Unknown grade");
}
```

### Pętle

```java
// For loop
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}

// While loop
int count = 0;
while (count < 5) {
    System.out.println(count);
    count++;
}

// Do-while loop
int x = 0;
do {
    System.out.println(x);
    x++;
} while (x < 5);
```

## Podstawy programowanie obiektowego

### Definicja klasy

```java
public class Person {
    // Instance variables (fields)
    private String name;
    private int age;
    
    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Methods
    public void sayHello() {
        System.out.println("Hello, my name is " + name);
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}
```

## Inicjalizacja instancji obiektu



### 1. Konstruktor

```java
// Create a new Person object using constructor
Person person1 = new Person("Alice", 30);
```

### 2. Konstruktor domyślny

```java
public class Book {
    private String title;
    private String author;
    
    // Default constructor
    public Book() {
        title = "Unknown";
        author = "Anonymous";
    }
}

// Usage
Book book1 = new Book();
```

### 3. Konstruktor sparametryzowany

```java
public class Rectangle {
    private double width;
    private double height;
    
    // Parameterized constructor
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}

// Usage
Rectangle rectangle1 = new Rectangle(5.0, 3.0);
```

### 4. Przeciążenie konstruktorów

```java
public class Student {
    private String name;
    private int id;
    private String major;
    
    // Constructor with all fields
    public Student(String name, int id, String major) {
        this.name = name;
        this.id = id;
        this.major = major;
    }
    
    // Constructor with just name and id
    public Student(String name, int id) {
        this(name, id, "Undeclared");  // Call the first constructor
    }
}

// Usage
Student student1 = new Student("Bob", 12345, "Computer Science");
Student student2 = new Student("Carol", 67890);  // Uses "Undeclared" as the default major
```

### 5. Blok inicjalizującvy

```java
public class Counter {
    private int count;
    
    // Instance initialization block
    {
        count = 1;  // Set initial value
        System.out.println("Initializing counter");
    }
    
    public Counter() {
        System.out.println("Counter constructor called");
    }
}

// Usage
Counter counter = new Counter();
// Prints: "Initializing counter" followed by "Counter constructor called"
```

### 6. Inicjalizacja wartości statycznych

```java
public class Configuration {
    // Static fields
    private static String serverName;
    private static int port;
    
    // Static initialization block
    static {
        serverName = "localhost";
        port = 8080;
        System.out.println("Static initialization complete");
    }
}

// The static block runs when the class is loaded
```

## Dobre praktyki inicjalizacji obiektów

1. **Sprawdzanie poprawności parametrów konstruktora**
2. **Używanie sensownych wartości domyślnych**

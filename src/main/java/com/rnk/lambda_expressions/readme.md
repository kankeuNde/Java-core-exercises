# Lambda Expressions Practice - Progressive Exercises

**Context:** Intermediate Java Developer – building skills around functional programming and clean code.

**Goal:** Master Java lambda expressions through realistic mini-projects while applying software engineering best practices (SOLID, TDD, modular design, robustness, and maintainability).

***

## Getting Started
### Git and Branch Setup
Before starting, create a dedicated branch for this series of exercises:

```bash
git checkout -b feature/lambda-practice
```

You will commit and push your progress to this branch after each exercise.

Each exercise builds upon the previous one — ensure your code is modular and tested so that refactoring remains easy.

***

## Exercise 1 – Basic Lambda Expressions

### **Business Context**
A small e-commerce startup wants to optimize its product filtering system. Currently, filtering uses strategy classes with repetitive boilerplate. You will refactor this using lambda expressions.

### **Objective**
Implement a simple filtering feature for a `Product` list using lambda expressions instead of anonymous classes.

### **Classes to Create**
#### `Product`
```java
public class Product {
    private final String name;
    private final double price;
    private final String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}
```

### **Your Task**
1. Write a `ProductFilter` class with a method:
   ```java
   public List<Product> filter(List<Product> products, Predicate<Product> predicate)
   ```
2. Use lambda expressions to filter:
    - products cheaper than a certain price,
    - products of a given category.

### **Testing (TDD)**
Start by writing JUnit tests **first**:
```java
@Test
void shouldFilterProductsCheaperThanSpecifiedPrice() {
    // Given
    List<Product> products = List.of(new Product("Laptop", 1500, "Electronics"),
                                     new Product("Book", 20, "Books"));

    // When
    List<Product> result = productFilter.filter(products, p -> p.getPrice() < 100);

    // Then
    assertEquals(1, result.size());
    assertEquals("Book", result.get(0).getName());
}
```

### **Good Practices**
- **Single Responsibility:** `Product` only represents data; `ProductFilter` only handles filtering.
- **TDD:** Write the test first, then implement enough code to pass.
- **Code cleanliness:** Keep methods short, clear, and pure.

### **Definition of Done (DoD)**
- All tests green
- Code conforms to SRP
- Clear naming and comments
- Git commit message: “Implement basic product filter using lambda expressions”

### **Git instructions**
```bash
git add .
git commit -m "Implement basic product filter using lambda expressions"
git push origin feature/lambda-practice
```

***

## Exercise 2 – Extending with Functional Interfaces

### **Business Context**
The startup wants customizable discounts. Instead of new classes, they want to define discount strategies inline using lambdas.

### **Objective**
Implement a simple discount engine supporting lambda-based strategies.

### **Classes to Create**
#### `DiscountService`
```java
public class DiscountService {
    public double applyDiscount(Product product, Function<Product, Double> discountStrategy) {
        double discount = discountStrategy.apply(product);
        return product.getPrice() - discount;
    }
}
```

### **Tasks**
- Create multiple lambda strategies for discounts:
    - 10% discount on electronics
    - Flat 5-unit discount for books

### **Testing (TDD)**
Example test:
```java
@Test
void shouldApplyTenPercentDiscountOnElectronics() {
    Product laptop = new Product("Laptop", 1000, "Electronics");

    DiscountService ds = new DiscountService();
    double finalPrice = ds.applyDiscount(laptop, p -> p.getPrice() * 0.10);

    assertEquals(900, finalPrice, 0.01);
}
```

### **SOLID Practice**
- **Open/Closed Principle:** Add new discounts without altering existing code.
- **Interface Segregation:** Keep your lambdas focused on one purpose.

### **Definition of Done (DoD)**
- Code passes unit tests
- Discount behavior easily modifiable
- No business logic duplication
- Git commit message: “Add DiscountService with lambda strategies”

### **Git instructions**
```bash
git add .
git commit -m "Add DiscountService with lambda strategies"
git push origin feature/lambda-practice
```

***

## Exercise 3 – Refactoring with Streams

### **Business Context**
As the product list grows, filtering and discounting need refactoring for chaining operations (filter → transform → aggregate).

### **Objective**
Use `Stream` API combined with lambdas for expressive code.

### **Tasks**
1. Refactor filtering plus discounting logic to use streams.
2. Compute the **average final price** for all discounted products.

### **Testing (TDD)**
Write tests to verify:
- Correct filter + discount chain results
- Empty lists handled gracefully

Example:
```java
@Test
void shouldComputeAverageDiscountedPrice() {
    List<Product> list = List.of(new Product("Laptop", 1000, "Electronics"),
                                 new Product("Book", 50, "Books"));
    double avg = list.stream()
                     .filter(p -> p.getPrice() > 100)
                     .mapToDouble(p -> p.getPrice() * 0.9)
                     .average()
                     .orElse(0);

    assertEquals(900, avg, 0.01);
}
```

### **Good Practices**
- Use pure functions and immutable results.
- Watch for `Optional` values to avoid nulls.
- Log processing steps using simple logging (e.g., `Logger.info`).

### **Definition of Done (DoD)**
- Efficient stream usage
- No side effects in lambdas
- Tests green
- Git commit message: “Refactor to use Stream API and lambdas”

### **Git instructions**
```bash
git add .
git commit -m "Refactor to use Stream API and lambdas"
git push origin feature/lambda-practice
```

***

## Exercise 4 – Robustness and Error Handling

### **Business Context**
A customer reports system crashes when products lack categories or have invalid prices.

### **Objective**
Integrate **graceful error handling** within lambda constructs.

### **Tasks**
- Validate null or negative price data before processing.
- Catch possible exceptions with robust error handling (ensure purity of lambdas where possible).
- Use custom functional interfaces when exceptions are possible.

### **Testing (TDD)**
Add tests to confirm:
- Invalid data does not crash the stream.
- A fallback value (or skipped entry) is used.

Example:
```java
@Test
void shouldSkipInvalidProductsGracefully() {
    Product invalid = new Product("Book", -10, null);
    List<Product> list = List.of(invalid, new Product("Pen", 2, "Stationery"));

    List<Product> validProducts = list.stream()
                                      .filter(p -> p.getPrice() > 0 && p.getCategory() != null)
                                      .collect(Collectors.toList());

    assertEquals(1, validProducts.size());
}
```

### **Best Practices**
- Avoid too much logic in lambdas.
- Create separate validator classes if logic expands.
- Follow SRP strictly.

### **Definition of Done (DoD)**
- All validations covered by tests
- No unhandled exceptions
- Improved resilience

### **Git instructions**
```bash
git add .
git commit -m "Add robust validation handling for lambdas"
git push origin feature/lambda-practice
```

***

## Exercise 5 – Extensibility & Clean Architecture

### **Business Context**
The startup’s scope grows. They need flexible reporting pipelines combining lambdas and functional operations.

### **Objective**
Build an extensible reporting module that can:
- Compute aggregate metrics (average price, total value)
- Support new metrics easily (Open/Closed Principle)

### **Classes to Add**
#### `ReportGenerator`
```java
public class ReportGenerator {
    public double generateReport(List<Product> products, ToDoubleFunction<Product> metricFunction) {
        return products.stream()
                       .mapToDouble(metricFunction)
                       .average()
                       .orElse(0);
    }
}
```

### **Testing (TDD)**
```java
@Test
void shouldComputeAveragePriceUsingReportGenerator() {
    List<Product> list = List.of(new Product("A", 100, "A"), new Product("B", 200, "B"));
    ReportGenerator gen = new ReportGenerator();

    double avg = gen.generateReport(list, Product::getPrice);

    assertEquals(150, avg, 0.01);
}
```

### **Refactoring Suggestions**
- Introduce **dependency injection** if behavior grows.
- Ensure **Liskov Substitution Principle** by keeping `Product` immutable.

### **Definition of Done (DoD)**
- ReportGenerator tested with multiple metric functions
- Clean architecture maintained
- Code reusable, scalable

### **Git instructions**
```bash
git add .
git commit -m "Implement extensible ReportGenerator with lambda metrics"
git push origin feature/lambda-practice
```
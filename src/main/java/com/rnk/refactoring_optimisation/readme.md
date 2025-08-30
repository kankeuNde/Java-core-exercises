```markdown
# Java Refactoring and Optimization Training Exercises

This training is designed for an **intermediate Java developer** who wants to improve skills in **refactoring, optimization, and software engineering best practices**.  
The exercises follow a **progressive mini-project approach**, where each step builds on the previous one, while applying **TDD (Test-Driven Development), SOLID principles, clean code practices, error handling, and agile methodology aspects (Scrum / Definition of Done).**

---

## General Instructions

1. **Git Workflow Setup**
   - Create a new branch before starting:
     ```
     git checkout -b feature/refactoring-training
     ```
   - After each exercise, commit and push your changes:
     ```
     git add .
     git commit -m "Exercise X - [short summary]"
     git push origin feature/refactoring-training
     ```

2. **Definition of Done (DoD) for each exercise**
   - ✅ Code is written using **TDD** (start with tests → implementation).  
   - ✅ Code follows **SOLID** principles (especially SRP).  
   - ✅ Unit tests reach at least **80% coverage** (JUnit).  
   - ✅ Proper **error handling** and **logging** are implemented.  
   - ✅ Code is **clean, modular, and extensible**.  
   - ✅ Code is committed and pushed to Git.  

---

# 📘 Exercise 1: Basic Customer Management

### Context
You are developing a small **Customer Management Module** for an e-commerce application. Your first task is to implement a simple entity to represent customers.

### Requirements
- Create a `Customer` class with the following attributes:
  - `private String id` (unique identifier, non-null)
  - `private String name` (mandatory, non-empty)
  - `private String email` (mandatory, valid email format)

### Validation Criteria
- SRP: `Customer` must only hold data, no business logic.
- Provide **constructor and getters only** (immutability encouraged).
- Write unit tests validating:
  - Creating a valid `Customer`.
  - Exceptions thrown when invalid data is provided.

### Example JUnit Tests to Start (TDD)
```
@Test
void shouldCreateValidCustomer() {
Customer customer = new Customer("1", "Alice", "alice@test.com");
assertEquals("Alice", customer.getName());
}

@Test
void shouldThrowExceptionForInvalidEmail() {
assertThrows(IllegalArgumentException.class,
() -> new Customer("2", "Bob", "invalid-email"));
}
```

### Definition of Done
- Customer entity created with validation checks.
- Tests pass with full coverage of invalid inputs.

### Git Instructions
```
git add .
git commit -m "Exercise 1 - Implement Customer entity with validation and unit tests"
git push origin feature/refactoring-training
```

---

# 📘 Exercise 2: Customer Repository and Error Handling

### Context
Now we want to **persist customers** in memory. Later this can be extended to a database.

### Requirements
- Create an interface `CustomerRepository` with methods:
  - `void save(Customer customer)`
  - `Optional<Customer> findById(String id)`
  - `List<Customer> findAll()`
- Implement `InMemoryCustomerRepository`.
- Handle possible errors:
  - Prevent adding customers with duplicate IDs → throw `DuplicateCustomerException`.
  - Return `Optional.empty()` if customer not found.
- Introduce **logging** (e.g., using `java.util.logging.Logger`) when saving or errors occur.

### Validation Criteria
- SRP: Repository only handles **storage**, not validation of email/name.
- TDD: Start by writing failing tests first.
- Exception hierarchy:
  - `CustomerException` (base class, extends `RuntimeException`)
  - `DuplicateCustomerException` (extends `CustomerException`)

### Example JUnit Tests
```
@Test
void shouldSaveAndRetrieveCustomer() {
CustomerRepository repo = new InMemoryCustomerRepository();
Customer customer = new Customer("1", "Alice", "alice@test.com");
repo.save(customer);
assertTrue(repo.findById("1").isPresent());
}

@Test
void shouldThrowWhenSavingDuplicateCustomer() {
CustomerRepository repo = new InMemoryCustomerRepository();
Customer customer = new Customer("1", "Alice", "alice@test.com");
repo.save(customer);
assertThrows(DuplicateCustomerException.class, () -> repo.save(customer));
}
```

### Git Instructions
```
git add .
git commit -m "Exercise 2 - Implement CustomerRepository with error handling and tests"
git push origin feature/refactoring-training
```

---

# 📘 Exercise 3: Customer Service Layer (Business Logic + TDD)

### Context
We now want to separate the **business rules** from the repository.  
Write a `CustomerService` class that:
- Uses `CustomerRepository`.
- Provides methods:
  - `Customer registerCustomer(String id, String name, String email)`
  - `Customer getCustomer(String id)`
  - `List<Customer> listCustomers()`

### Requirements
- Handle exceptions at service level.
  - Throw `CustomerNotFoundException` if `getCustomer` fails.
- Log important actions/outcomes.
- Maintain **clean separation of concerns**.

### Validation Criteria
- Service does **not** manage persistence details (delegates to repository).
- Tests should use **Fake Repository** (in-memory).
- Business logic errors are distinct from repository exceptions.

### Example JUnit Tests
```
@Test
void shouldRegisterCustomerSuccessfully() {
CustomerRepository repo = new InMemoryCustomerRepository();
CustomerService service = new CustomerService(repo);

    Customer registered = service.registerCustomer("1", "Alice", "alice@test.com");
    assertEquals("Alice", registered.getName());
}

@Test
void shouldThrowWhenCustomerNotFound() {
CustomerRepository repo = new InMemoryCustomerRepository();
CustomerService service = new CustomerService(repo);

    assertThrows(CustomerNotFoundException.class, () -> service.getCustomer("999"));
}
```

### Git Instructions
```
git add .
git commit -m "Exercise 3 - Implement CustomerService with business rules and TDD"
git push origin feature/refactoring-training
```

---

# 📘 Exercise 4: Refactoring with SOLID & Extensibility

### Context  
The customer management module should be extensible for future use (e.g., adding a **DatabaseRepository** later).  
You will refactor your code to improve **extensibility and adherence to SOLID**.

### Refactoring Steps
- Apply **Dependency Inversion Principle (DIP)**:
  - `CustomerService` must **depend on the `CustomerRepository` interface**, not the concrete `InMemoryCustomerRepository`.
- Introduce **Factory Pattern** for repository instantiation.
- Introduce **CustomLogger wrapper class** instead of using the raw logger everywhere:
  - `CustomLogger.logInfo(String message)`
  - `CustomLogger.logError(String message, Exception e)`

### Validation Criteria
- No violation of **SRP**: services handle business, repository handles persistence, logger handles logging.
- Ensure test coverage remains ≥80%.
- Ensure exception hierarchy is clear (business vs. infrastructure).

### Example JUnit Tests
```
@Test
void shouldUseRepositoryInterfaceForService() {
CustomerRepository fakeRepo = new InMemoryCustomerRepository();
CustomerService service = new CustomerService(fakeRepo);

    Customer customer = service.registerCustomer("10", "Eve", "eve@test.com");
    assertTrue(service.listCustomers().contains(customer));
}
```

### Git Instructions
```
git add .
git commit -m "Exercise 4 - Refactor with SOLID (DIP, Factory, Logger) and tests"
git push origin feature/refactoring-training
```

---

# 📘 Exercise 5: Error Handling and Extensibility with a Realistic Feature

### Context
Your team wants to add **email notification** when a customer registers.  
We simulate this with a `NotificationService`.

### Requirements
- Create interface `NotificationService`:
  - `void sendWelcomeEmail(Customer customer)`
- Implement a mock version `FakeNotificationService` for testing.
- Integrate into `CustomerService.registerCustomer()`.
- Ensure **errors in notification do not break customer registration** (catch exceptions, log warnings).

### Validation Criteria
- Follow **Open-Closed Principle (OCP)** → Notification could later be SMS, push, etc.
- Use **Mocking** in tests (with Mockito).
- **Error resilience**: customer is still created even if email fails.

### Example JUnit Test
```
@Test
void shouldRegisterCustomerEvenIfNotificationFails() {
CustomerRepository repo = new InMemoryCustomerRepository();
NotificationService failingNotifier = customer -> { throw new RuntimeException("SMTP Down"); };

    CustomerService service = new CustomerService(repo, failingNotifier);

    Customer customer = service.registerCustomer("20", "John", "john@test.com");
    assertEquals("John", customer.getName());
}
```

### Git Instructions
```
git add .
git commit -m "Exercise 5 - Add NotificationService with error handling and TDD"
git push origin feature/refactoring-training
```

---

# ✅ Final Checklist: Best Practices Validation

Before considering your mini-project **DONE**, check the following:

- [ ] TDD workflow followed (tests before implementation).  
- [ ] All classes follow **SRP** (single well-defined responsibility).  
- [ ] Code adheres to **SOLID** principles:
  - S: Separation of responsibilities
  - O: Open for extension, closed for modification
  - L: Liskov Substitution (services can accept any impl. of `CustomerRepository`)
  - I: Interface segregation (NotificationService small and purpose-specific)
  - D: Dependency Inversion (dependencies via interfaces, injected not hardcoded)
- [ ] Clear and consistent **exception hierarchy**.  
- [ ] Proper logging for major operations & error cases.  
- [ ] All JUnit tests passing, ≥80% coverage.  
- [ ] Code reviewed, formatted, and pushed to Git.  
- [ ] Each exercise committed separately for traceability.  

---

🚀 Next Steps: Extend this project into a **modular e-commerce platform** with Orders, Payments, etc., applying the same approach.  
```
Below is a progressive set of Java exercises focusing on generics, designed for intermediate developers. 
Each builds upon the previous one to create a mini-project evolving in complexity, with attention to TDD, SOLID principles (including SRP), clean code, error management, and Scrum practices like Definition of Done. Exercises are clearly stated with realistic context, explicit attributes for classes, and include guidance for test cases and Git usage.

# Java Generics Training Exercises with Industrial Best Practices

## Initial Setup: Git Branch Instructions

Before you start the exercises, create a dedicated Git branch to save all your work related to Java generics:

```bash
git checkout -b feature/generics-training
```

Make commits progressively following each exercise instructions and push to remote:

```bash
git add .
git commit -m "Exercise X: short descriptive message"
git push origin feature/generics-training
```

## Exercise 1: Generic Container Class for a Business Data Holder

### Context

You are developing a component of a data processing application which requires a simple container class to hold a single business entity of any type (e.g., Customer, Product, Order). The class should be generic and allow storing and retrieving the entity safely with type checks at compile time.

### Task

Write a generic class called `Container` with the following:

- An attribute: `private T item;`
- A constructor accepting a single `T item` parameter.
- A method `getItem()` returning the stored object.
- A method `setItem(T item)` to replace the stored object.

### Validation Criteria

- Unit tests must cover setting and getting different types (e.g., String, Integer, custom classes).
- The class must follow the Single Responsibility Principle: only manage storing and retrieving the item.
- Use TDD: start by writing unit tests (JUnit 5) before implementation.
- Clearly separate test and main code packages.
- Handle null values gracefully (e.g., disallow null on setItem by throwing `IllegalArgumentException`).
- Maintain clean, readable code with proper JavaDoc comments.
- Include logging of operations using a simple logger pattern (e.g., Java’s `java.util.logging.Logger`).

### Suggested Test Cases (JUnit)

- Setting and getting a String.
- Setting and getting an Integer.
- Setting a null item should throw `IllegalArgumentException`.
- Changing stored item and verifying the update.

### Scrum Aspect: Definition of Done (DoD)

- All tests pass with 100% coverage.
- Code reviewed for SRP compliance.
- Logging included for each public method.
- Code is pushed to the Git branch with meaningful commit message.
- Pull request created with self-review checklist.

### Git Commit Example

```bash
git add src/main/java/... Container.java src/test/java/... ContainerTest.java
git commit -m "Exercise 1: Implement generic Container with tests and basic validation"
git push origin feature/generics-training
```

## Exercise 2: Generic Pair Class with Business Use Case

### Context

In your application, you need to manage pairs of related objects, such as product-code and product-description pairs, or customer-id and customer-email pairs.

### Task

Create a generic class `Pair` representing a pair of two objects of types `K` and `V` with:

- Attributes:
    - `private K key;`
    - `private V value;`
- A constructor with `Pair(K key, V value)`.
- Getters: `getKey()` and `getValue()`.
- Setters: `setKey(K key)`, `setValue(V value)`.
- Override `equals()` and `hashCode()` methods based on both fields.
- Override `toString()` to display key and value elegantly.

### Validation Criteria

- Test creation and retrieval of pairs of different types.
- TDD approach: write tests before implementation.
- SRP: Class only manages the pair data, no business logic.
- Ensure immutability by optionally providing a variant with only getters and no setters.
- Handle null keys or values properly (reject null keys, allow null values).
- Maintain clean, modular code with Javadoc.
- Add logging for constructor and setters.
- Usage of design patterns: consider the Builder pattern for immutability (optional for advanced practice).

### Suggested Test Cases (JUnit)

- Create pairs with `(String, Integer)` and `(Integer, String)` types.
- Verify `equals()` and `hashCode()` considering pairs with same and different data.
- Check exception on null key.

### Scrum Aspect: Definition of Done

- Code coverage at least 90%.
- Code clean and modular with logs.
- Tests validate all behaviors, including edge cases.
- Proper documentation for public API.
- Commit with suitable descriptive message and push to Git.

### Git Commit Example

```bash
git add src/main/java/... Pair.java src/test/java/... PairTest.java
git commit -m "Exercise 2: Implement generic Pair with equals, hashCode, tests and logging"
git push origin feature/generics-training
```

## Exercise 3: Generic Repository Interface and Implementation

### Context

You need a reusable data repository interface for managing entities of various types, such as Customers or Orders, stored in memory.

### Task

Define a generic interface:

```java
public interface Repository {
    void save(T entity);
    T findById(ID id);
    List findAll();
    void deleteById(ID id);
}
```

Then implement this interface in a class `InMemoryRepository` using a `Map` for storage.

- Attributes for `InMemoryRepository`:
    - `private Map storage = new HashMap<>();`

- Methods should perform the respective operations, throwing custom exceptions when appropriate (e.g., `EntityNotFoundException` if `findById` or `deleteById` receives unknown ID).

- Define `EntityNotFoundException` as a custom unchecked exception.

### Validation Criteria

- TDD: Create test mocks/fakes for entities.
- Respect SRP: Separate exception classes, repository interface, and implementation.
- Clean code with modular packages.
- Robust error handling and clear exception messages.
- Use logging for operations and exceptions.
- Cover edge cases: saving duplicate IDs, deleting missing IDs.
- Unit tests cover all CRUD operations.

### Suggested Test Cases (JUnit)

- Save entities and retrieve by ID.
- Fetch all entities.
- Delete existing entity by ID.
- Attempt to delete non-existing entity throws exception.
- Attempt to find non-existing entity throws exception.

### Scrum Aspect: Definition of Done

- Automated tests cover all methods and exceptions.
- Proper separation of concerns and packages.
- Exception class documented.
- Logging integrated.
- Push committed code to Git branch.

### Git Commit Example

```bash
git add src/main/java/... Repository.java InMemoryRepository.java EntityNotFoundException.java src/test/java/... InMemoryRepositoryTest.java
git commit -m "Exercise 3: Create generic Repository interface and InMemory implementation with exception and tests"
git push origin feature/generics-training
```

## Exercise 4: Extending Generic Repository with Filtering and Error Handling

### Context

You want to extend generic repository functionality to allow retrieving entities that satisfy arbitrary conditions, such as customers from a certain city or orders above a price threshold.

### Task

- Extend `Repository` with a method:

```java
List findBy(Predicate filter);
```

- Implement this method in `InMemoryRepository`.

- Create a business entity class `Customer` for test purposes with attributes:

    - `private Integer id;`
    - `private String name;`
    - `private String city;`

- Add validation inside repository methods to ensure IDs are not null (throw `IllegalArgumentException` otherwise).

- Handle exceptions gracefully and log warnings or errors.

### Validation Criteria

- Tests verify filter logic correctly returns matching entities.
- Tests cover repository behavior on null or invalid inputs.
- Follow SRP: repository does not handle entity logic.
- Logging details for filtering process.
- Code is modular and reusable.

### Suggested Test Cases (JUnit)

- Save multiple `Customer` objects; test filtering by city.
- Test filter with empty results.
- Test findBy with null predicate should throw exception.

### Scrum Aspect: Definition of Done

- Code passes all tests including edge cases.
- Logging is in place.
- Documentation updated for new methods.
- Committed and pushed to Git.

### Git Commit Example

```bash
git add src/main/java/... Repository.java InMemoryRepository.java Customer.java src/test/java/... InMemoryRepositoryFilterTest.java
git commit -m "Exercise 4: Add filtering capabilities to generic repository with validation and tests"
git push origin feature/generics-training
```

## Exercise 5: Refactoring for SOLID, Scalability and Use of Design Patterns

### Context

Your generic repository grows in complexity. You decide to improve code for scalability and adaptability.

### Task

- Apply Dependency Injection to repository implementation to inject a `Logger` instance rather than creating it internally.

- Introduce the `Specification` design pattern to formalize filtering criteria instead of publishing raw `Predicate` directly:

```java
public interface Specification {
    boolean isSatisfiedBy(T item);
}
```

- Adapt repository to support specifications:

```java
List findBySpecification(Specification specification);
```

- Refactor code for better SRP: separate concerns into well-defined classes.

- Increase unit test coverage for new structures, mock injected dependencies if needed.

### Validation Criteria

- Clean separation of concerns.
- Flexible filtering system.
- Robust exception and error handling.
- Mocking in tests to isolate behavior.
- Logging via injected logger only.
- Code easily extendable to other persistence mechanisms.

### Suggested Test Cases (JUnit, Mockito)

- Test repository with mock logger.
- Test `Specification` implementations.
- Verify that injecting null logger throws an exception.
- Test filtering using specification instead of predicate.

### Scrum Aspect: Definition of Done

- Branch reviewed for SOLID adherence.
- Tests include mock-based isolation.
- Documentation updated.
- Code pushed to Git.

### Git Commit Example

```bash
git add src/main/java/... Specification.java src/main/java/... Repository.java InMemoryRepository.java src/test/java/... SpecificationTest.java InMemoryRepositorySpecTest.java
git commit -m "Exercise 5: Refactor repository with Specification pattern and DI with Logger including tests"
git push origin feature/generics-training
```

# Best Practices Checklist for Your Solutions

- [ ] Followed TDD: wrote unit tests before code.
- [ ] Code respects SRP and other SOLID principles.
- [ ] Code is modular and classes have single responsibilities.
- [ ] Unit tests cover normal cases, edge cases, and error cases.
- [ ] Proper exception handling and use of custom exceptions.
- [ ] Logging included with meaningful messages.
- [ ] Code is clean, readable, and well-documented.
- [ ] Git commits are atomic and descriptive.
- [ ] Definition of Done criteria met for each exercise.
- [ ] Use of mocks/fakes where appropriate for tests.
- [ ] Code easily extensible and maintainable.

This progression ensures you practice Java generics deeply with strict attention to industrial software engineering best practices, covering TDD, clean code, SOLID principles, error management, modularity, and Scrum methodologies while building practical components.

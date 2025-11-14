README.md

# Singleton Design Pattern Training Exercises

## Introduction: Real-life analogy and problem

Imagine a single office printer shared among many employees. To avoid conflicts and ensure proper usage, there's only one physical printer accessible. Similarly, the Singleton design pattern in software ensures there is exactly one instance of a class, providing a global point of access to it. This prevents multiple instances from causing resource conflicts (e.g., multiple database connections or logging instances).

***

## Git Branch Setup

Before starting exercises, create a dedicated Git branch to save your work incrementally.

```bash
git checkout -b feature/singleton-pattern
```

Save your code after each exercise with:

```bash
git add .
git commit -m "Completed Exercise X - Description"
git push origin feature/singleton-pattern
```

***

## Exercise 1: Basic Singleton Implementation

### Context

You need to create a logging utility that should only have one instance across the application to avoid duplicate log files.

### Requirements

- Implement a thread-safe Singleton class `Logger` with a private constructor.
- Provide a `getInstance()` method.
- Implement a simple method `log(String message)` that prints the message with a timestamp.
- Use lazy initialization.
- Write JUnit tests to verify only one instance is ever created.
- Use mocks if necessary to test logging output.
- Keep the class single responsibility: only logging related.

### Validation Criteria

- Singleton instance created lazily and thread-safe.
- Only one instance possible.
- Unit tests cover normal and multithreaded scenarios.
- SRP respected, well-named methods, and clean code.

### Test cases suggestions (JUnit)

- Test multiple `getInstance()` calls return the same instance.
- Test concurrent access to `getInstance()` returns the same instance.
- Test `log()` outputs the expected formatted message.

***

## Exercise 2: Extensible Singleton with Configuration

### Context

Extend the Logger to support different log levels (INFO, WARNING, ERROR) and be configurable at runtime for output format.

### Requirements

- Modify `Logger` to allow setting a log level.
- Add a method to set the output format (e.g., JSON, plain text).
- Ensure Singleton pattern and thread safety remain intact.
- Follow SRP by delegating formatting to a separate `Formatter` class.
- Include exception handling for invalid configurations.
- Write comprehensive unit tests for configuration.
- Use Dependency Injection where meaningful to increase modularity.

### Validation Criteria

- Configuration changes affect all log calls globally.
- Proper error handling on bad configuration.
- Clean modular code following SRP and SOLID.
- Full test coverage for configuration paths.

### Test cases suggestions

- Test different log level settings suppress or allow messages properly.
- Test setting invalid format throws exceptions.
- Test concurrent configuration changes handled safely.

***

## Exercise 3: Singleton with Resource Management and Robustness

### Context

Create a database connection manager singleton ensuring only one connection pool is instantiated and configurable for connection limits.

### Requirements

- Implement `DatabaseConnectionManager` as Singleton.
- Support lazy initialization.
- Enable configuration settings for max connections.
- Implement robust error handling and resource cleanup.
- Write unit tests using mocks/fakes for database connections.
- Support evolving design (extensibility) for future protocol changes.
- Follow clean code and SOLID principles strictly.

### Validation Criteria

- Singleton enforces single connection pool.
- Proper resource management preventing leaks.
- Modular and testable code.
- Complete test coverage including failure states.
- Code ready for extension/refactor per future requirements.

***

## Scrum Definition of Done (DoD) for Each Exercise

- Code committed and pushed with meaningful messages.
- Unit tests with 80%+ coverage.
- Code reviewed or self-reviewed for style.
- All acceptance criteria met.
- Passing build and tests.
- README updated if necessary.

***

## Good Practices Checklist

- [ ] Singleton created with private constructor.
- [ ] getInstance method thread-safe and lazy or eager as decided.
- [ ] SOLID principles respected (especially SRP).
- [ ] Unit tests cover normal and edge cases.
- [ ] Exceptions handled gracefully.
- [ ] Modular code with clear responsibilities.
- [ ] Logging and error handling implemented.
- [ ] Code pushed on correct Git branch with commits.
- [ ] Documentation/comments clear and concise.

***

This progressive exercise series builds your skills on the Singleton pattern deeply, incorporating real-world concerns like thread safety, modularity, extensibility, and test-driven quality. Following the instructions and using test-driven development will help you master the pattern with confidence.

[1](https://www.geeksforgeeks.org/system-design/singleton-design-pattern/)
[2](https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples)
[3](https://stackoverflow.com/questions/2475380/example-for-singleton-pattern)
[4](https://www.programiz.com/java-programming/singleton)
[5](https://www.geeksforgeeks.org/java/singleton-class-java/)
[6](https://refactoring.guru/design-patterns/singleton/java/example)
[7](https://refactoring.guru/design-patterns/singleton)
[8](https://www.reddit.com/r/learnprogramming/comments/raecp9/what_is_an_example_of_the_singleton_design_pattern/)
[9](https://sourcemaking.com/design_patterns/singleton/java/1)
[10](https://www.youtube.com/watch?v=khnair65cVc)
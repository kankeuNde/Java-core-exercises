git Here are the translations of the previous two exercises on the Builder Pattern in Java, rendered in English as requested, following the structure and style compatible with README.md format:

***

## Context and Real-Life Analogy

Imagine you want to build a **car**. The car can have different configurations: electric or combustion engine, leather or fabric seats, premium or standard equipment. The manufacturer does not want to write complicated logic for all these configurations in a single constructor.

The **Builder Pattern** allows you to construct the car step-by-step according to the desired configuration, making the code flexible and modular. It is like a car factory where each step (installing engine, seats, etc.) is separated, and the factory can produce different models without changing the overall process.

***

## Exercise 1: Building a Customized Computer

### Business Context

A computer configuration company wants to allow customers to create their custom PCs with options for CPU, RAM, storage, GPU, etc. The goal is to make this configuration flexible and easily extensible.

### Technical Goals

- Implement the **Builder Pattern** to build a `Computer` object.
- Use a **Fluent Builder** to simplify syntax.
- Respect SOLID principles by separating responsibilities.
- Write JUnit tests to validate each step.
- Handle errors (e.g., unsupported configurations).

### Specification

| Classes (excerpt)               | Role / Responsibility                   | Attributes                   | Methods                    | Example Signature                                |
|--------------------------------|---------------------------------------|------------------------------|----------------------------|-------------------------------------------------|
| `Computer`                     | Final product                         | cpu, ram, storage, gpu        | displayInfo()              | `public void displayInfo()`                      |
| `Builder`                     | Abstract builder interface            | —                            | buildCPU(), buildRAM(), buildStorage(), buildGPU(), getResult() | `public interface Builder { ... }`              |
| `ConcreteBuilder` (e.g., `GamingComputerBuilder`) | Concrete implementation              | same attributes as Computer  | Specific build methods    | `public class GamingComputerBuilder implements Builder` |
| `Director`                   | Orchestrates build steps               | —                            | construct(Builder)         | `public class ComputerDirector { ... }`         |
| Unit Tests                   | Verify complete configuration          | —                            | —                          | e.g., `testGamingComputer()`                     |

### Tips and Best Practices

- Define a clear `Builder` interface.
- The `Computer` class should be immutable or only configured via the builder.
- Create a `Director` to control the construction order.
- Write tests first (TDD).
- Handle exceptions if unsupported configuration is requested.

### JUnit Test Cases

- Build a gaming PC with GPU, 16GB RAM, SSD, verify configuration correctness.
- Verify error handling when invalid configuration is requested.
- Confirm `displayInfo()` prints correct configuration details.

### Git Instructions

```shell
git checkout -b builder-exercise1
```

Commit often with `git add .` and `git commit -m "..."`.

***

## Exercise 2: Modular Industrial Robot Construction

### Business Context

A robotics factory wants to assemble different types of robots (soldier, assistant, drone) with configurable components: sensors, weapons, batteries, etc. Each robot type has its own configuration, but the build process is similar.

### Technical Goals

- Implement a **Builder** for different robot types.
- Construction must be modular and extensible.
- Each component respects the SRP.
- TDD tests for each build step.
- Include error handling and logging.

### Specification

| Classes (excerpt)               | Role / Responsibility                   | Attributes                   | Methods                    | Signature Example                               |
|--------------------------------|---------------------------------------|------------------------------|----------------------------|------------------------------------------------|
| `Robot`                        | Final product                         | components: sensors, weapons, power | displayComponents()        | `public void displayComponents()`               |
| `RobotBuilder`                | Construction interface                 | —                            | buildSensors(), buildWeapons(), buildPower(), getRobot() | `public interface RobotBuilder { ... }`         |
| `SoldierRobotBuilder`, `DroidRobotBuilder` | Concrete builders                    | same as Robot attributes     | Specific build methods    | `public class SoldierRobotBuilder implements RobotBuilder` |
| `RobotDirector`               | Director                              | —                            | construct(RobotBuilder)    | `public class RobotDirector { ... }`             |
| Unit Tests                   | Verify robot components                | —                            | —                          | e.g., `testSoldierRobot()`                       |

### Tips and Best Practices

- Use interfaces to anticipate extension.
- Check error handling with specific exceptions.
- Cover each build step with unit tests, including error simulation.
- Maintain cohesion and avoid duplication.

### JUnit Test Cases

- Build a soldier robot and verify full configuration.
- Test handling a defective component (e.g., corrupted battery).
- Check `displayComponents()` shows proper details.

### Git Instructions

```shell
git checkout -b builder-exercise2
```

Commit regularly after changes.

***

## Conclusion

These two exercises introduce and deepen understanding of the Builder Pattern: how to build complex objects step-by-step while respecting SOLID, TDD, modularity, error management, and extensibility.[4][9][10]

The progressive approach ensures mastery of fundamental concepts, preparing you for robust, scalable industrial projects.

***

If desired, detailed code skeletons, interfaces, or method contracts can be provided for each step.

[1](https://stackoverflow.com/questions/23169505/java-best-way-to-implement-builder-pattern)
[2](https://www.javacodegeeks.com/2013/01/the-builder-pattern-in-practice.html)
[3](https://refactoring.guru/design-patterns/builder)
[4](https://refactoring.guru/design-patterns/builder/java/example)
[5](https://blog.stackademic.com/what-is-the-builder-pattern-in-java-and-how-does-it-benefit-your-code-07e2675048b9)
[6](https://mkyong.com/design-pattern/java-builder-design-pattern-example/)
[7](https://www.digitalocean.com/community/tutorials/builder-design-pattern-in-java)
[8](https://developer.vonage.com/en/blog/builder-pattern-with-inheritance-in-java)
[9](https://www.baeldung.com/java-builder-pattern)
[10](https://dzone.com/articles/design-patterns-builder)
The Abstract Factory Pattern helps solve real-world problems where systems need to create families of related products without tightly coupling client code to concrete implementations. For example, imagine you're building a cross-platform GUI library: Windows, macOS, and Linux each need distinct buttons, menus, and other widgets, but your application should be able to switch "style" without changing its core logic. The Abstract Factory pattern enables this flexibility, ensures type consistency, and supports scalable code evolution.[3][5][7]

Below are three progressive mini-project exercises that deepen understanding of Abstract Factory in Java while integrating professional best practices and compatible with typical Scrum workflow.

***

## Starter Instructions: Git Branch Setup

Before starting, create a new Git branch for your Abstract Factory training development:

```shell
git checkout -b abstract-factory-training
```

- Commit each completed exercise to this branch as described in the instructions for every exercise.

***

## Exercise 1: Device Factory Basics

### Context

You work for a tech company developing software for home automation. Devices (Lights, Thermostats) come in two brands: SmartTech and EcoHome. Each brand has different protocols for device setup.

You are to model this using the Abstract Factory Pattern in Java, ensuring clean separation and code extensibility.

### Requirements

- Implement an Abstract Factory ("DeviceFactory") able to create Lights and Thermostats for each brand.
- Device objects should expose a setup() method showing a brand-specific setup message.
- Apply SRP: Each class should have a single responsibility.
- Use TDD: Define JUnit tests before implementing logic.
- Ensure exception handling for unsupported brands.

#### Class Overview

| Class                  | Role/Responsibility                                 | Attributes                             | Methods                               | Contract/Skeleton            |
|------------------------|-----------------------------------------------------|----------------------------------------|---------------------------------------|------------------------------|
| `DeviceFactory` (interface) | Abstract Factory for creating devices                  | —                                      | Light createLight(), Thermostat createThermostat() | ```java interface DeviceFactory { Light createLight(); Thermostat createThermostat(); } ```
| `Light` (interface)    | Abstraction for lights                              | —                                      | void setup()                          | ```java interface Light { void setup(); } ```
| `Thermostat` (interface)| Abstraction for thermostats                        | —                                      | void setup()                          | ```java interface Thermostat { void setup(); } ```
| `SmartTechFactory`     | Factory implementation for SmartTech devices        | —                                      | createLight(), createThermostat()     | ```java class SmartTechFactory implements DeviceFactory { ... } ```
| `EcoHomeFactory`       | Factory implementation for EcoHome devices          | —                                      | createLight(), createThermostat()     | ```java class EcoHomeFactory implements DeviceFactory { ... } ```
| `SmartTechLight`, `SmartTechThermostat`, `EcoHomeLight`, `EcoHomeThermostat` | Concrete products                      | —                                      | setup() (brand-specific message)      | ```java class SmartTechLight implements Light { ... } ```

#### SRP and SOLID Reminders

- Each device and factory implements one clear responsibility.
- Use interfaces for abstraction (OCP).
- No business logic in factories—just instantiation.

#### Example Case Tests (JUnit)

1. Instantiating a SmartTech Light through SmartTechFactory returns a Light whose setup message includes "SmartTech".
2. Instantiating an EcoHome Thermostat through EcoHomeFactory returns proper brand setup.
3. Requesting an unsupported brand throws a custom DeviceFactoryException.

#### Scrum Definition of Done

- Code compiles with 100% passing unit tests.
- SRP (single responsibility) respected.
- No duplicate or dead code.
- Javadoc present for all classes.
- Exception handling implemented for unsupported brands.
- Code committed to Git.

#### Git Instructions

```shell
git add .
git commit -m "Exercise 1: Device Factory implementation and tests"
```

***

## Exercise 2: Adding Error Handling and Logging

### Context

The company’s QA team found issues with device setup failures due to network problems. You must enhance your factories so that each device’s setup() can handle errors gracefully and log them for troubleshooting.

### Requirements

- Update device classes to simulate and handle setup failures (e.g., by randomly throwing a SetupFailedException in setup()).
- Add logging (using Java Logger) in setup() methods.
- Exception flow: Factories instantiate device objects; devices handle their own setup and log errors.
- Extend unit tests with TDD to verify exception handling and logging.

#### Class Overview

| Class                  | Additional Attributes/Methods                | Details                               |
|------------------------|----------------------------------------------|---------------------------------------|
| `SetupFailedException` | Custom Exception Class                       | Message and cause                     |
| `Light`, `Thermostat`  | Add setup() throws SetupFailedException      | Simulate errors, log outcomes         |

##### Example of Light interface (updated):

```java
interface Light {
    void setup() throws SetupFailedException;
}
```

#### SOLID/Best Practices

- Exception classes are responsible only for error messaging (SRP).
- Use dependency injection for Logger if possible.
- Do not catch exceptions unless handling is meaningful (avoid swallowing).

#### Example Case Tests (JUnit)

1. Simulate failed setup and verify SetupFailedException is thrown.
2. Confirm logs include error messages at appropriate severity level.
3. No device class leaks implementation details to client code (Test uses mocks/fakes where logging checked).

#### Scrum Definition of Done

- All new tests for error scenarios pass.
- Logging integrated and tested.
- Exception handling does not break SRP or OCP.
- Branch pushed to Git.

#### Git Instructions

```shell
git add .
git commit -m "Exercise 2: Error handling and logging for device setup"
```

***

## Exercise 3: Extending with New Product Types and Refactoring

### Context

Business now offers new devices (SecurityCamera, MotionSensor) with similar branding and setup needs. The code needs refactoring for extensibility and maintaining SOLID principles.

### Requirements

- Refactor Abstract Factory to allow addition of devices without modifying existing factories (use interface segregation).
- Add SecurityCamera and MotionSensor abstractions and implementations for both brands.
- Ensure code modularity.
- Update tests for new device types, mocking setup failures and verifying exception/logging coverage.
- Refactor factories so new device additions follow OCP (Open-Closed Principle).

#### Class Overview

| Class                   | Role/Responsibility                        | Attributes            | Methods                   | Contract/Skeleton        |
|-------------------------|--------------------------------------------|-----------------------|---------------------------|--------------------------|
| `SecurityCamera` (interface) | Abstraction for camera device                | —                     | setup() throws SetupFailedException | ```java interface SecurityCamera { void setup() throws SetupFailedException; } ```
| `MotionSensor` (interface) | Abstraction for sensor device                | —                     | setup() throws SetupFailedException | ```java interface MotionSensor { void setup() throws SetupFailedException; } ```
| Appropriate Factory updates | Methods to create new products                | —                     | createSecurityCamera(), createMotionSensor() | Factory interface to include methods for new devices |

#### SOLID/Best Practices

- Refactor Abstract Factory interface for scalability (Interface Segregation, OCP).
- Maintain SRP in all product/device implementations.
- Factories easily accommodate new devices with minimal code changes.

#### Example Case Tests (JUnit)

1. Devices of each type and brand can be created and setup with correct log outputs.
2. Setup failures for MotionSensor and SecurityCamera are correctly handled and logged.
3. Adding new device type requires only new interface/implementation; existing code not modified beyond extension.

#### Scrum Definition of Done

- 100% test coverage including new devices and error flows.
- Code refactored per OCP and modularized.
- Javadoc present.
- Git branch up-to-date.

#### Git Instructions

```shell
git add .
git commit -m "Exercise 3: New device types and OCP-compliant factories"
```

***

### Real-life Analogy

The Abstract Factory pattern is like a kitchen appliance store that bundles products by brand. If a customer asks for all "EcoHome" appliances, the clerk fetches the matching fridge, oven, and toaster from EcoHome—without needing to know the specific production process. If you add a new product, say "EcoHome blender", the store simply adds it to its bundle—other store sections (Windows, SmartTech) remain unaffected.

***

Apply the above exercises stepwise, following best Java and Scrum/industrial practices for robust, extensible, and readable code. This format ensures every learning step builds upon previous code. Use the branch and commit instructions to keep code safely versioned.[1][5][3]

[1](https://www.digitalocean.com/community/tutorials/abstract-factory-design-pattern-in-java)
[2](https://ideo.bretagne.bzh/formations/java-se-intermediaire-conception-objet-avancee-design-pattern)
[3](https://java-design-patterns.com/patterns/abstract-factory/)
[4](https://www.youtube.com/watch?v=Tp0oPksu--E)
[5](https://refactoring.guru/design-patterns/abstract-factory/java/example)
[6](https://www.jmdoudoux.fr/java/dej/chap-design-patterns.htm)
[7](https://www.geeksforgeeks.org/system-design/abstract-factory-design-pattern-in-java/)
[8](https://mgasquet.github.io/R304-QualiteDeveloppement/tutorials/tutorial4)
[9](https://www.cs.mcgill.ca/~adenau/teaching/cs303/lecture18.pdf)
[10](https://fr.scribd.com/document/852856185/DEVOIR-A4)
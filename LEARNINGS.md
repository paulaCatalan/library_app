# LEARNINGS
## Learnings from planning development

### Kotlin
#### Types of classes

| Name of the class | Definition | In this repository |
| :--- | :--- | :--- |
| **`class`** | The standard class for general object-oriented programming, capable of having properties, methods, and constructors. | Almost all files in this repository contain standard classes. |
| **`data class`** | Specialized for holding/representing data. The compiler automatically generates useful methods like `.equals()`, `.hashCode()`, `.toString()`, and `.copy()`, which is ideal for model and DTO classes. | In this repository, there are data classes in the domain package and dto packages. |
| **`enum class`** | Represents a fixed set of constants. Each enum constant is an object. They are perfect for when you have a small, finite number of possible values. | In this repository, there are no enums currently. An idea of an enum could be representing a state of reading progress of a book for a user (e.g. `COMPLETE`, `STARTED`, `NOT_STARTED`, `IN_PROGRESS`). |
| **`sealed class`** | Used for representing restricted class hierarchies (a fixed set of types). It's powerful when used with `when` expressions, as the compiler can ensure all cases are handled. | Not used in this repository (for now). |
| **`nested class`** | A class defined inside another class. It cannot access the members of the outer class. You can think of it as being namespaced inside the outer class. | Not used in this repository (for now). |
| **`inner class`** | A nested class marked with the `inner` keyword. It holds a reference to an object of the outer class and can access its members. | Not used in this repository (for now). |

#### Null Safety

Kotlin's type system distinguishes between nullable and non-nullable types, 
helping to prevent NullPointerException errors at compile time.

This implies the usage of some operators across the code:

| Operator | Name | Description |
| :--- | :--- | :--- |
| **`?.`** | Safe Call Operator | Executes a call only if the value is not `null`; otherwise, it returns `null`. This is the safest way to access properties or methods on a nullable object. |
| **`?:`** | Elvis Operator | Provides a default value to use if the expression on its left is `null`. |
| **`!!`** | Not-Null Assertion | Forcefully converts a value to a non-null type, throwing a `NullPointerException` if the value is `null`. Should be used with extreme caution. |

#### Extension Functions

A powerful feature to add new functionality to existing classes without modifying their original source code. 
This helps keep data classes clean while still allowing for related helper functions.
Over-using these kind of functions is dangerous and can grow in a difficult-to-navigate code.

### Spring Boot
In simple terms, Spring Boot is a framework that makes it much easier and faster to build stand-alone, production-ready applications using the core Spring Framework.

There are some specific words relevant to understand how Spring Boot works:

- **Dependency Injection (DI) and Inversion of Control (IoC):**
In simple terms, Dependency Injection (or "DI") is Spring's way of automatically providing a class with the objects it needs to do its job.
Instead of you creating the repository instance manually inside the service class, you let Spring Boot do it, through the constructor. 
This concept is part of a larger principle called Inversion of Control (IoC). 
You are "inverting" the control. Instead of your code controlling its dependencies, you hand that control over to the Spring framework. 
You simply declare what you need, and Spring figures out how to provide it. This makes your classes much more flexible, decoupled, and easier to test.

- **Bean:** a Bean is simply an object that is instantiated, assembled, and managed by the Spring IoC (Inversion of Control) container.
The key takeaway is that a bean is not a special kind of object; 
it's a regular object whose entire lifecycle—from creation to destruction—is handled by the Spring framework. This allows Spring to do powerful things like automatically "injecting" one bean into another (Dependency Injection).

#### Core Annotations

- **`@SpringBootApplication`:** The entry point that combines configuration, component scanning, and auto-configuration.
  - Compulsory in the main application class.

#### Stereotype Annotations

### Design and Architecture

### Web development

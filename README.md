# Logger Framework

A custom logging framework built with multiple design patterns for learning purposes.

## 🎯 Quick Start

### Basic Usage
```java
Logger logger = LoggerFactory.getLogger(MyClass.class);
logger.info("Hello World");
logger.error("Something went wrong");
logger.debug("Debug information");
```

### Custom Configuration with Builder
```java
Logger logger = LoggerFactory.builder(MyClass.class)
    .withConsoleAppender()
    .withLoggerAppender()
    .build();

logger.info("Custom configured logger");
```

## 🏗️ Design Patterns Used

### 1. Factory Pattern
**Location:** `LoggerFactory`

Centralizes logger creation and provides a simple API for obtaining logger instances.

**Implementation:**
```java
public class LoggerFactory {
    private static final Map<String, Logger> loggers = new HashMap<>();

    public static Logger getLogger(Class<?> clazz) {
        return loggers.computeIfAbsent(clazz.getName(), 
                name -> LoggerBuilder.create(name)
                        .withDefaultAppenders()
                        .build());
    }
}
```

**Usage:**
```java
Logger logger = LoggerFactory.getLogger(MyClass.class);
```

**Benefits:**
- Hides construction complexity
- Single point of access for logger creation
- Easy to change creation logic

---

### 2. Builder Pattern
**Location:** `LoggerBuilder`

Provides a fluent API for constructing logger instances with different configurations.

**Implementation:**
```java
public class LoggerBuilder {
    private final String name;
    private final List<Appender> appenders;

    public LoggerBuilder withConsoleAppender() {
        appenders.add(new ConsoleAppender(new SimpleFormatter()));
        return this;
    }

    public LoggerBuilder withLoggerAppender() {
        appenders.add(new LoggerAppender());
        return this;
    }

    public Logger build() {
        return new Logger(name, appenders);
    }
}
```

**Usage:**
```java
Logger logger = LoggerFactory.builder(MyClass.class)
    .withConsoleAppender()
    .withLoggerAppender()
    .build();
```

**Benefits:**
- Readable, chainable method calls
- Flexible configuration without constructor overloading
- Clear separation of construction logic

---

### 3. Singleton Pattern
**Location:** `LoggerFactory` (Logger Cache)

Ensures only one logger instance exists per class name, reusing existing instances.

**Implementation:**
```java
public class LoggerFactory {
    private static final Map<String, Logger> loggers = new HashMap<>();

    public static Logger getLogger(Class<?> clazz) {
        // Returns existing logger or creates new one
        return loggers.computeIfAbsent(clazz.getName(), 
                name -> LoggerBuilder.create(name)
                        .withDefaultAppenders()
                        .build());
    }
}
```

**Usage:**
```java
Logger logger1 = LoggerFactory.getLogger(MyClass.class);
Logger logger2 = LoggerFactory.getLogger(MyClass.class);
// logger1 == logger2 (same instance)
```

**Benefits:**
- Memory efficient (no duplicate loggers)
- Consistent logger instances across application
- Improved performance

---

### 4. Strategy Pattern
**Location:** `Appender` interface

Defines a family of algorithms (logging destinations) that are interchangeable.

**Implementation:**
```java
public interface Appender {
    void append(LogRecord record);
}

public class ConsoleAppender implements Appender {
    private final SimpleFormatter formatter;

    @Override
    public void append(LogRecord record) {
        System.out.println(formatter.format(record));
    }
}

public class LoggerAppender implements Appender {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger("SLF4J Wala Appender");

    @Override
    public void append(LogRecord record) {
        switch (record.getLevel()) {
            case INFO:
                logger.info(record.getMessage());
                break;
            // ... other levels
        }
    }
}
```

**Usage:**
```java
Logger logger = LoggerFactory.builder(MyClass.class)
    .withAppender(new ConsoleAppender(new SimpleFormatter()))
    .withAppender(new LoggerAppender())
    .withAppender(new CustomAppender())  // Easy to add new strategies
    .build();
```

**Benefits:**
- Easy to add new appenders without modifying existing code
- Runtime selection of logging strategies
- Follows Open/Closed Principle

---

## 📦 Components

### Logger
Main logging interface with methods for different log levels.

```java
logger.info("Information message");
logger.warn("Warning message");
logger.error("Error message");
logger.debug("Debug message");
logger.trace("Trace message");
```

### Appenders
- **ConsoleAppender** - Logs to console (System.out)
- **LoggerAppender** - Delegates to SLF4J/Spring Boot logger

### Formatter
- **SimpleFormatter** - Formats logs as: `[timestamp] [level] [thread] message`

### LogRecord
Immutable data object containing log information (level, message, timestamp, thread, logger name).

## 🔧 Builder Methods

| Method | Description |
|--------|-------------|
| `withConsoleAppender()` | Add console appender with default formatter |
| `withConsoleAppender(formatter)` | Add console appender with custom formatter |
| `withLoggerAppender()` | Add SLF4J appender |
| `withAppender(appender)` | Add any custom appender |
| `withDefaultAppenders()` | Add both Console and SLF4J appenders |
| `build()` | Build the logger instance |

## 💡 Usage Examples

### Example 1: Console Only
```java
Logger logger = LoggerFactory.builder(MyClass.class)
    .withConsoleAppender()
    .build();
```

### Example 2: SLF4J Only
```java
Logger logger = LoggerFactory.builder(MyClass.class)
    .withLoggerAppender()
    .build();
```

### Example 3: Multiple Appenders
```java
Logger logger = LoggerFactory.builder(MyClass.class)
    .withConsoleAppender()
    .withLoggerAppender()
    .build();
```

### Example 4: Custom Appender
```java
Logger logger = LoggerFactory.builder(MyClass.class)
    .withAppender(new MyCustomAppender())
    .build();
```


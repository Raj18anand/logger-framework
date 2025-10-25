package org.codebook.loggerframework;

import java.util.ArrayList;
import java.util.List;

public class LoggerBuilder {
    private final String name;
    private final List<Appender> appenders;

    private LoggerBuilder(String name) {
        this.name = name;
        this.appenders = new ArrayList<>();
    }

    public static LoggerBuilder create(String name) {
        return new LoggerBuilder(name);
    }

    public static LoggerBuilder create(Class<?> clazz) {
        return new LoggerBuilder(clazz.getName());
    }

    public LoggerBuilder withConsoleAppender(SimpleFormatter formatter) {
        appenders.add(new ConsoleAppender(formatter));
        return this;
    }

    public LoggerBuilder withConsoleAppender() {
        appenders.add(new ConsoleAppender(new SimpleFormatter()));
        return this;
    }

    public LoggerBuilder withLoggerAppender() {
        appenders.add(new LoggerAppender());
        return this;
    }

    public LoggerBuilder withAppender(Appender appender) {
        appenders.add(appender);
        return this;
    }

    public LoggerBuilder withDefaultAppenders() {
        appenders.add(new ConsoleAppender(new SimpleFormatter()));
        appenders.add(new LoggerAppender());
        return this;
    }

    public Logger build() {
        if (appenders.isEmpty()) {
            withDefaultAppenders();
        }
        return new Logger(name, appenders);
    }
}

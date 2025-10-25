package org.codebook.loggerframework;

import java.util.HashMap;
import java.util.Map;

public class LoggerFactory {
    private static final Map<String, Logger> loggers = new HashMap<>();

    public static Logger getLogger(Class<?> clazz) {
        return loggers.computeIfAbsent(clazz.getName(), 
                name -> LoggerBuilder.create(name)
                        .withDefaultAppenders()
                        .build());
    }

    public static Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, 
                n -> LoggerBuilder.create(n)
                        .withDefaultAppenders()
                        .build());
    }

    public static LoggerBuilder builder(Class<?> clazz) {
        return LoggerBuilder.create(clazz);
    }

    public static LoggerBuilder builder(String name) {
        return LoggerBuilder.create(name);
    }
}

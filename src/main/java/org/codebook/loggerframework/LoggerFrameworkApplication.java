package org.codebook.loggerframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggerFrameworkApplication {
    
    public static void main(String[] args) {
        Logger logger1 = LoggerFactory.getLogger(LoggerFrameworkApplication.class);

        Logger logger2 = LoggerFactory.builder("CustomLogger")
                .withConsoleAppender()
                .build();

        Logger logger3 = LoggerFactory.builder("SLF4JOnly")
                .withLoggerAppender()
                .build();
        
        SpringApplication.run(LoggerFrameworkApplication.class, args);
        
        logger1.info("Application started successfully");
        logger1.error("Application failed to start");
        logger1.debug("Loading...");
        
        logger2.info("This is from custom logger (console only)");
        logger3.info("This is from SLF4J only logger");
    }

}

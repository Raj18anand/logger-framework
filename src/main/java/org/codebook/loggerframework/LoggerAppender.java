package org.codebook.loggerframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerAppender implements Appender {
    private final Logger logger = LoggerFactory.getLogger("SLF4J Wala Appender");

    public void append(LogRecord record){
        switch (record.getLevel()){
            case INFO:
                logger.info(record.getMessage());
                break;
            case ERROR:
                logger.error(record.getMessage());
                break;
            case WARN:
                logger.warn(record.getMessage());
                break;
            case DEBUG:
                logger.debug(record.getMessage());
                break;
            case TRACE:
                logger.trace(record.getMessage());
                break;
        }
    }
}

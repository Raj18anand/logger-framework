package org.codebook.loggerframework;

import lombok.Data;

@Data
public class LogRecord {
    private final String message;
    private final LogLevel level;
    private final long timeStamp;
    private final String threadName;
    private final String loggerName;

    public LogRecord(LogLevel level, String message, String loggerName){
        this.level=level;
        this.message=message;
        this.timeStamp=System.currentTimeMillis();
        this.threadName=Thread.currentThread().getName();
        this.loggerName=loggerName;
    }
}

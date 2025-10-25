package org.codebook.loggerframework;

import java.util.List;

public class Logger {
    private final String name;
    private final List<Appender> appenders;

    public Logger(String name, List<Appender> appenders){
        this.name=name;
        this.appenders=appenders;
    }

    private void log(LogLevel level, String message){
        LogRecord record= new LogRecord(level, message, name);
        for(Appender appender: appenders){
            appender.append(record);
        }
    }

    public void info(String message){
        log(LogLevel.INFO, message);
    }

    public void debug(String message){
        log(LogLevel.DEBUG, message);
    }

    public void warn(String message){
        log(LogLevel.WARN, message);
    }

    public void error(String message){
        log(LogLevel.ERROR, message);
    }

    public void trace(String message){
        log(LogLevel.TRACE, message);
    }
}

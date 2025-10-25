package org.codebook.loggerframework;

public class ConsoleAppender implements Appender {
    private final SimpleFormatter formatter;

    public ConsoleAppender(SimpleFormatter formatter){
        this.formatter=formatter;
    }

    public void append(LogRecord record){
        System.out.println(formatter.format(record));
    }
}

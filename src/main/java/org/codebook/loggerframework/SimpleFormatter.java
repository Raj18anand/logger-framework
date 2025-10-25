package org.codebook.loggerframework;

import org.springframework.stereotype.Component;

@Component
public class SimpleFormatter {
    public String format(LogRecord record){
        return String.format("[%tF %<tT] [%s] [%s] %s",
                record.getTimeStamp(), record.getLevel(),
                record.getThreadName(), record.getMessage());
    }
}

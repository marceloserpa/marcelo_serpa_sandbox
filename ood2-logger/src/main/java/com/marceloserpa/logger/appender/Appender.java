package com.marceloserpa.logger.appender;

import com.marceloserpa.logger.LogEvent;

public interface Appender {

    void append(LogEvent logEvent);

    default String format(LogEvent logEvent){
        return String.format("%s - %s - [%s] - %s",
                logEvent.instant(), logEvent.logLevel(), logEvent.clazz(), logEvent.message());
    }

}

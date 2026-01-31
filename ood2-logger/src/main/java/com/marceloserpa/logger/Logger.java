package com.marceloserpa.logger;

import com.marceloserpa.logger.appender.Appender;
import com.marceloserpa.logger.executor.Executor;

import java.time.Instant;

public class Logger {

    private final Executor executor;
    private final Appender appender;

    public Logger(Executor executor, Appender appender) {
        this.executor = executor;
        this.appender = appender;
    }

    public void log(String message, LogLevel logLevel) {
        executor.execute(appender, new LogEvent(logLevel, message, Instant.now()));
    }

    public void info(String message){
        log(message, LogLevel.INFO);
    }

    public void debug(String message){
        log(message, LogLevel.DEBUG);
    }

    public void error(String message){
        log(message, LogLevel.ERROR);
    }
}

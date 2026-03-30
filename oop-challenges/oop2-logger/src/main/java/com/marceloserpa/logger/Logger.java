package com.marceloserpa.logger;

import com.marceloserpa.logger.appender.Appender;
import com.marceloserpa.logger.appender.ConsoleAppender;
import com.marceloserpa.logger.executor.AsyncExecutor;
import com.marceloserpa.logger.executor.Executor;
import com.marceloserpa.logger.executor.SyncExecutor;

import java.time.Instant;
import java.util.Objects;

public class Logger {

    private final Executor executor;
    private final Appender appender;
    private final Class clazz;

    public Logger(Executor executor, Appender appender, Class clazz) {
        this.executor = executor;
        this.appender = appender;
        this.clazz = clazz;
    }

    public void log(String message, LogLevel logLevel) {
        executor.execute(appender, new LogEvent(logLevel, message, Instant.now(), this.clazz));
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

    public static class Builder {

        private Executor executor;
        private Appender appender;
        private Class clazz;

        public Builder withAppender(Appender appender) {
            this.appender = appender;
            return this;
        }

        public Builder withClass(Class clazz) {
            this.clazz = clazz;
            return this;
        }

        public Builder enableAsync(){
            this.executor = new AsyncExecutor();
            return this;
        }

        public Logger build() {
            Objects.requireNonNull(this.clazz);
            if (this.executor == null) {
                this.executor = new SyncExecutor();
            }

            if(this.appender == null){
                this.appender = new ConsoleAppender();
            }

            return new Logger(this.executor, this.appender, this.clazz);
        }

    }


}

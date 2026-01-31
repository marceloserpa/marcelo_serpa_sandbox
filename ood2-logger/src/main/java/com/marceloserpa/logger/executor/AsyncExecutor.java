package com.marceloserpa.logger.executor;

import com.marceloserpa.logger.LogEvent;
import com.marceloserpa.logger.appender.Appender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncExecutor implements Executor{

    ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void execute(Appender appender, LogEvent logEvent) {
        executor.submit(() -> appender.append(logEvent));
    }
}

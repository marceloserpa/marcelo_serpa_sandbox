package com.marceloserpa.logger.executor;

import com.marceloserpa.logger.LogEvent;
import com.marceloserpa.logger.appender.Appender;

public class SyncExecutor implements Executor{

    @Override
    public void execute(Appender appender, LogEvent logEvent) {
        appender.append(logEvent);
    }
}

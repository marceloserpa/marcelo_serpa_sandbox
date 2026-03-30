package com.marceloserpa.logger.executor;

import com.marceloserpa.logger.LogEvent;
import com.marceloserpa.logger.appender.Appender;

public interface Executor {

    void execute(Appender appender, LogEvent logEvent);
}

package com.marceloserpa.logger.appender;

import com.marceloserpa.logger.LogEvent;

public class ConsoleAppender implements Appender{

    @Override
    public void append(LogEvent logEvent) {
        System.out.println(format(logEvent));
    }
}

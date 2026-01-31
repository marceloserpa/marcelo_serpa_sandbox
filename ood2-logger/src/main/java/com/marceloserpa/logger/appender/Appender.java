package com.marceloserpa.logger.appender;

import com.marceloserpa.logger.LogEvent;

public interface Appender {

    void append(LogEvent logEvent);

}

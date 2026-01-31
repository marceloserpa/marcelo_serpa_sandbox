package com.marceloserpa.logger;


import com.marceloserpa.logger.appender.Appender;
import com.marceloserpa.logger.appender.ConsoleAppender;
import com.marceloserpa.logger.executor.Executor;
import com.marceloserpa.logger.executor.SyncExecutor;

public class Main {
    static void main() {

        Executor executor = new SyncExecutor();
        Appender appender = new ConsoleAppender();
        Logger logger = new Logger(executor, appender);
        logger.info("Marcelo");
        logger.debug("Serpa");
        logger.error("Test");

    }
}

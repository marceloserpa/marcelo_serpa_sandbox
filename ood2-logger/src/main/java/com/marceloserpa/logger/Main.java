package com.marceloserpa.logger;


import com.marceloserpa.logger.appender.Appender;
import com.marceloserpa.logger.appender.ConsoleAppender;
import com.marceloserpa.logger.executor.AsyncExecutor;
import com.marceloserpa.logger.executor.Executor;
import com.marceloserpa.logger.executor.SyncExecutor;

public class Main {
    static void main() throws InterruptedException {
        Executor executor = new SyncExecutor();
        Appender appender = new ConsoleAppender();
        Logger logger = new Logger(executor, appender, Main.class);
        logger.info("Marcelo");
        logger.debug("Serpa");
        logger.error("Test");

        System.out.println("== 1 ================================");

        Logger loggerAsync = new Logger(new AsyncExecutor(), new ConsoleAppender(), Main.class);
        loggerAsync.info("Marcelo");
        loggerAsync.debug("Serpa");
        loggerAsync.error("Test");

        Thread.sleep(3000);
        System.out.println("== 2 ================================");


        Logger loggerAsync2 = new Logger.Builder().withClass(Main.class)
                .withAppender(new ConsoleAppender())
                .enableAsync()
                .build();
        loggerAsync2.info("Marcelo");
        loggerAsync2.debug("Serpa");
        loggerAsync2.error("Test");

    }
}

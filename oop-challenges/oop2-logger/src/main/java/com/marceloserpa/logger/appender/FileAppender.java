package com.marceloserpa.logger.appender;

import com.marceloserpa.logger.LogEvent;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FileAppender implements Appender{

    private final BufferedWriter writer;

    public FileAppender(Path path) {
        try {
            this.writer = Files.newBufferedWriter(path, CREATE, APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void append(LogEvent logEvent) {
        try {
            writer.append(format(logEvent));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

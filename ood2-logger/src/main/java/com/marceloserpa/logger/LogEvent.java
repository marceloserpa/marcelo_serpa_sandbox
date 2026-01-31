package com.marceloserpa.logger;

import java.time.Instant;

public record LogEvent(LogLevel logLevel, String message, Instant instant) {}

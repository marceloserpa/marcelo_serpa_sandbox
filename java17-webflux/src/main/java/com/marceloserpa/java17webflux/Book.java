package com.marceloserpa.java17webflux;

import java.math.BigDecimal;

public record Book (long id, String title, String author, BigDecimal price){ }

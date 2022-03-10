package com.marceloserpa.java17webflux;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("books")
public record BookEntity (@Id long id, String title, String author, BigDecimal price){ }
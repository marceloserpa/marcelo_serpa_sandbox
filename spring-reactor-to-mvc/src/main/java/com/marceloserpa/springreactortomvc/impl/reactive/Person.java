package com.marceloserpa.springreactortomvc.impl.reactive;

import org.springframework.data.annotation.Id;

public record Person(@Id Long id, String name, String lastname) {
}

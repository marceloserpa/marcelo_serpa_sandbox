package com.marceloserpa.springreactortomvc.impl.blocking;

import org.springframework.data.annotation.Id;

public record Person(@Id Long id, String name, String lastname) {
}

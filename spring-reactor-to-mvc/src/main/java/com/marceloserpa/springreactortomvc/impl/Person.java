package com.marceloserpa.springreactortomvc.impl;

import org.springframework.data.annotation.Id;

public record Person(@Id Long id, String name, String lastname) {
}

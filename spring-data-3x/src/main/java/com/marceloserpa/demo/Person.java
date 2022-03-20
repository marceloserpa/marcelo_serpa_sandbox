package com.marceloserpa.demo;

import org.springframework.data.annotation.Id;

public record Person(@Id long id, String name, String lastname) {};

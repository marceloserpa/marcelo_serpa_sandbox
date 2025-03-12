package com.marceloserpa.spring_transaction_poc;

import org.springframework.data.annotation.Id;

public record Person(@Id long id, String name, String lastname) {};
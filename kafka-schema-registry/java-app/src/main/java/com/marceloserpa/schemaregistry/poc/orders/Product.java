package com.marceloserpa.schemaregistry.poc.orders;

import java.math.BigDecimal;
import java.util.UUID;

public record Product (UUID id, String name, int quantity, BigDecimal unit_price){};
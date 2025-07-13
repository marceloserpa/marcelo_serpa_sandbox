package com.marceloserpa.schemaregistry.poc.orders;

import java.util.List;
import java.util.UUID;

public record Order(UUID customerID, UUID salesman, List<Product> products){}

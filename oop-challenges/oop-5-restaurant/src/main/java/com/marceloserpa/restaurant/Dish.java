package com.marceloserpa.restaurant;

import java.util.List;

public record Dish(String name, List<Step> preparationSteps) {
}

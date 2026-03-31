package com.marceloserpa.restaurant;

import java.util.EnumMap;
import java.util.Map;

public class Kitchen {

    private final Map<KitchenStationType, Integer> stationNextFree =
            new EnumMap<>(KitchenStationType.class);

    public Kitchen() {
        for (KitchenStationType type : KitchenStationType.values()) {
            stationNextFree.put(type, 0);
        }
    }

    public int estimate(Dish dish) {
        int currentTime = 0;

        for (Step step : dish.preparationSteps()) {
            int stationAvailable = stationNextFree.get(step.station());

            int start = Math.max(currentTime, stationAvailable);
            int finish = start + step.durationInMinutes();

            stationNextFree.put(step.station(), finish);
            currentTime = finish;
        }

        return currentTime;
    }
}

package com.marceloserpa.toxicproxypoc;

public record Character(
        Long id,
        String name,
        String height,
        String mass,
        String hairColor,
        String eyeColor,
        String birthYear
    ) {}

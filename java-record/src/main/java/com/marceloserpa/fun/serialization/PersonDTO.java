package com.marceloserpa.fun.serialization;


import java.time.LocalDate;

public record PersonDTO(String name, int age, double weight,
                        LocalDate collegeApplicationDate) {
}
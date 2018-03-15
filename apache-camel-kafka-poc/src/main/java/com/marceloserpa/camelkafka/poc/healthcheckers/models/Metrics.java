package com.marceloserpa.camelkafka.poc.healthcheckers.models;

public class Metrics {

    private String status;

    public Metrics(String status) {
        this.status = status;
    }

    public Metrics() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

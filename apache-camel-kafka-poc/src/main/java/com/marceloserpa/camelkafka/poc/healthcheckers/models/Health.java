package com.marceloserpa.camelkafka.poc.healthcheckers.models;

public class Health {

    private String status;

    private Metrics kafkaConsumer;

    private Metrics kafkaProducer;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Metrics getKafkaConsumer() {
        return kafkaConsumer;
    }

    public void setKafkaConsumer(Metrics kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    public Metrics getKafkaProducer() {
        return kafkaProducer;
    }

    public void setKafkaProducer(Metrics kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }
}

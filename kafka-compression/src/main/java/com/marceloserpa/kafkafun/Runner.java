package com.marceloserpa.kafkafun;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marceloserpa.kafkafun.order.Cart;
import com.marceloserpa.kafkafun.order.Order;
import com.marceloserpa.kafkafun.order.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class Runner implements CommandLineRunner {

    private KafkaTemplate<String, String> kafkaProducerWithoutCompression;
    private KafkaTemplate<String, String> kafkaProducerWithLZ4;
    private ObjectMapper objectMapper;
    
    // Limited set of 20 product names
    private static final String[] PRODUCT_NAMES = {
        "Laptop", "Smartphone", "Headphones", "Keyboard", "Mouse",
        "Monitor", "Tablet", "Camera", "Speaker", "Charger",
        "Cable", "Case", "Stand", "Adapter", "Battery",
        "Memory Card", "Hard Drive", "Router", "Webcam", "Microphone"
    };
    
    private static final String[] PAYMENT_METHODS = {
        "credit_card", "debit_card", "paypal", "bank_transfer", "apple_pay"
    };
    
    private static final String[] CURRENCIES = {"USD", "EUR", "BRL", "GBP"};
    
    private Random random = new Random();

    public Runner(KafkaTemplate<String, String> kafkaProducerWithoutCompression, KafkaTemplate<String, String> kafkaProducerWithLZ4) {
        this.kafkaProducerWithoutCompression = kafkaProducerWithoutCompression;
        this.kafkaProducerWithLZ4 = kafkaProducerWithLZ4;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started! Generating sample orders with performance evaluation...");

        runSimulation("none");
        runSimulation("lz4");
    }

    private void runSimulation(String compression) throws JsonProcessingException {

        int totalMessages = 10_000_000;

        int batchSize = 100;
        List<Long> messageTimes = new ArrayList<>();
        List<Long> batchThroughputs = new ArrayList<>();

        KafkaTemplate<String, String> producer = null;
        String topic = null;

        System.out.println("COMPRESSION=" + compression);
        if(compression.equals("lz4")) {
            producer = this.kafkaProducerWithLZ4;
            topic = "orders-compression-lz4";
        } else {
            producer = this.kafkaProducerWithoutCompression;
            topic = "orders-compression-none";
        }


        for(int i = 0; i < totalMessages; i++) {
            Order order = generateRandomOrder();
            String orderJson = objectMapper.writeValueAsString(order);

            // Send message and measure time
            producer.send(topic, orderJson);
        }

    }

    
    private Order generateRandomOrder() {
        Order order = new Order();
        
        // Generate random user ID
        order.setUserId(UUID.randomUUID());
        
        // Set current timestamp
        order.setDate(LocalDateTime.now());
        
        // Generate random cart with 1-5 items
        int cartSize = random.nextInt(5) + 1;
        List<Cart> cartItems = new ArrayList<>();
        
        for (int i = 0; i < cartSize; i++) {
            Cart cartItem = new Cart();
            cartItem.setId(UUID.randomUUID());
            cartItem.setName(PRODUCT_NAMES[random.nextInt(PRODUCT_NAMES.length)]);
            cartItem.setQuantity(random.nextInt(3) + 1); // 1-3 quantity
            cartItem.setPrice(generateRandomPrice());
            cartItems.add(cartItem);
        }
        order.setCart(cartItems);
        
        // Generate payment
        Payment payment = new Payment();
        payment.setMethod(PAYMENT_METHODS[random.nextInt(PAYMENT_METHODS.length)]);
        payment.setCurrency(CURRENCIES[random.nextInt(CURRENCIES.length)]);
        
        // Calculate total amount from cart
        BigDecimal totalAmount = cartItems.stream()
            .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        payment.setAmount(totalAmount);
        
        order.setPayment(payment);
        
        return order;
    }
    
    private BigDecimal generateRandomPrice() {
        // Generate price between 10.00 and 999.99
        double price = 10.0 + (random.nextDouble() * 989.99);
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }

}

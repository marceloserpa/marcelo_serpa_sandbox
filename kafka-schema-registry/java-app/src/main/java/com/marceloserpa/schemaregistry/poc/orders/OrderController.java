package com.marceloserpa.schemaregistry.poc.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        try {
            System.out.println("Received order: " + order);
            
            // Process the order and convert to Avro, then publish to Kafka
            orderService.processOrder(order);
            
            return ResponseEntity.ok("Order placed successfully");
        } catch (Exception e) {
            System.err.println("Error processing order: " + e.getMessage());
            return ResponseEntity.internalServerError().body("Failed to place order: " + e.getMessage());
        }
    }
}

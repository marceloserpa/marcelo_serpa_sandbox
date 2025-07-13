package com.marceloserpa.schemaregistry.poc.orders;

import com.marceloserpa.orders.events.Customer;
import com.marceloserpa.orders.events.OrderPlaced;
import com.marceloserpa.orders.events.Salesman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private KafkaTemplate<String, OrderPlaced> kafkaTemplate;

    private static final String TOPIC_NAME = "order-placed-events";

    public void processOrder(Order order) throws Exception{
        // Convert Order to OrderPlaced Avro object
        OrderPlaced orderPlaced = convertToOrderPlaced(order);
        
        // Publish to Kafka
        kafkaTemplate.send(TOPIC_NAME, orderPlaced.getOrderId(), orderPlaced).get();
        
        System.out.println("Order placed event published: " + orderPlaced.getOrderId());
    }

    private OrderPlaced convertToOrderPlaced(Order order) {
        // Generate event metadata
        String eventId = UUID.randomUUID().toString();
        String eventTimestamp = Instant.now().toString();
        String orderId = UUID.randomUUID().toString();

        // Create Customer (using placeholder data since Order doesn't have customer details)
        Customer customer = Customer.newBuilder()
                .setId(order.customerID().toString())
                .setName("Customer Name") // Placeholder - you might want to fetch this from a customer service
                .setEmail("customer@example.com") // Placeholder
                .build();

        // Create Salesman (using placeholder data since Order doesn't have salesman details)
        Salesman salesman = Salesman.newBuilder()
                .setId(order.salesman().toString())
                .setName("Salesman Name") // Placeholder - you might want to fetch this from a user service
                .build();

        // Convert Products
        List<com.marceloserpa.orders.events.Product> avroProducts = order.products().stream()
                .map(this::convertToAvroProduct)
                .collect(Collectors.toList());

        // Calculate total cost
        double totalCost = order.products().stream()
                .mapToDouble(product -> product.quantity() * product.unit_price().doubleValue())
                .sum();

        // Build OrderPlaced Avro object
        return OrderPlaced.newBuilder()
                .setEventId(eventId)
                .setEventTimestamp(eventTimestamp)
                .setOrderId(orderId)
                .setCustomer(customer)
                .setSalesman(salesman)
                .setProducts(avroProducts)
                .setTotalCost(totalCost)
                .build();
    }

    private com.marceloserpa.orders.events.Product convertToAvroProduct(Product product) {
        return com.marceloserpa.orders.events.Product.newBuilder()
                .setId(product.id().toString())
                .setName(product.name())
                .setQuantity(product.quantity())
                .setUnitPrice(product.unit_price().doubleValue())
                .build();
    }
}

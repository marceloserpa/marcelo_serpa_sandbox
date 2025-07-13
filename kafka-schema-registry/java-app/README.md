# Kafka Schema Registry POC

## Running the Application

1. Build the project:
   ```bash
   ./gradlew build
   ```

2. Run the Spring Boot application:
   ```bash
   ./gradlew bootRun
   ```

The application will start on port 9000.

## Testing

### Send a test order:

```bash
curl -X POST http://localhost:9000/orders \
  -H "Content-Type: application/json" \
  -d @sample-order-request.json
```

### Sample Request (sample-order-request.json):

```json
{
  "customerID": "123e4567-e89b-12d3-a456-426614174000",
  "salesman": "987fcdeb-51a2-43d7-8f9e-123456789abc",
  "products": [
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "name": "Laptop",
      "quantity": 2,
      "unit_price": 999.99
    },
    {
      "id": "550e8400-e29b-41d4-a716-446655440002",
      "name": "Mouse",
      "quantity": 2,
      "unit_price": 25.50
    }
  ]
}
```


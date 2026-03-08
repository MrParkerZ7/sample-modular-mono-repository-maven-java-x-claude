# REST Payment Service

Payment processing REST API service.

## Quick Start

```bash
# Run the service
mvn spring-boot:run -pl service/rest/payment

# Or build and run JAR
mvn clean package -pl service/rest/payment -am
java -jar service/rest/payment/target/rest-payment.jar
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/payments` | List all payments |
| GET | `/api/payments/{id}` | Get payment by ID |
| GET | `/api/payments/transaction/{transactionId}` | Get payment by transaction ID |
| GET | `/api/payments/order/{orderId}` | Get payments by order |
| GET | `/api/payments/user/{userId}` | Get payments by user |
| POST | `/api/payments` | Process payment |
| POST | `/api/payments/{id}/refund` | Refund payment |
| DELETE | `/api/payments/{id}` | Cancel payment |

## Port

- Default: `8086`

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>rest-payment</artifactId>
</dependency>
```

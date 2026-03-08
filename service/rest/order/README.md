# REST Order Service

Order processing REST API service.

## Quick Start

```bash
# Run the service
mvn spring-boot:run -pl service/rest/order

# Or build and run JAR
mvn clean package -pl service/rest/order -am
java -jar service/rest/order/target/rest-order.jar
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/orders` | List all orders |
| GET | `/api/orders/{id}` | Get order by ID |
| GET | `/api/orders/number/{orderNumber}` | Get order by number |
| GET | `/api/orders/user/{userId}` | Get orders by user |
| POST | `/api/orders` | Create order |
| PUT | `/api/orders/{id}` | Update order |
| PUT | `/api/orders/{id}/status/{status}` | Update order status |
| DELETE | `/api/orders/{id}` | Cancel order |

## Port

- Default: `8085`

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>rest-order</artifactId>
</dependency>
```

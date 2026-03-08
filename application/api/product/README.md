# REST Product Service

Insurance product catalog REST API service.

## Quick Start

```bash
# Run the service
mvn spring-boot:run -pl service/rest/product

# Or build and run JAR
mvn clean package -pl service/rest/product -am
java -jar service/rest/product/target/rest-product.jar
```

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/products` | List all products |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/code/{code}` | Get product by code |
| GET | `/api/products/type/{type}` | Get products by type |
| POST | `/api/products` | Create product |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |

## Port

- Default: `8084`

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>rest-product</artifactId>
</dependency>
```

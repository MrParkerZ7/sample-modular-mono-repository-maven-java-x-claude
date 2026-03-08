# SOAP Sample Service

Sample SOAP web service demonstrating Spring WS.

## Quick Start

```bash
# Run the service
mvn spring-boot:run -pl service/soap/sample

# Or build and run JAR
mvn clean package -pl service/soap/sample -am
java -jar service/soap/sample/target/soap-sample.jar
```

## Endpoints

| Path | Description |
|------|-------------|
| `/ws` | SOAP endpoint |
| `/ws/sample.wsdl` | WSDL definition |

## Port

- Default: `8081`

## Dependency

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>soap-sample</artifactId>
</dependency>
```

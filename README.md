# E-Commerce Web Application

[![CI](https://github.com/swarajsalve042/E-commorce-Web-application/actions/workflows/ci.yml/badge.svg)](https://github.com/swarajsalve042/E-commorce-Web-application/actions/workflows/ci.yml)

A Java 17 and Spring Boot REST API demonstrating product catalogue management with Spring Data JPA and an H2 in-memory database.

## Features

- Create, list and retrieve products.
- Update an existing product with `PUT /api/products/{id}`.
- Delete products by ID.
- Validate product names (1–100 characters) and positive prices (up to two decimal places).
- Trim leading and trailing whitespace from product names.
- Return appropriate HTTP status codes for invalid input and missing products.

## Requirements

- Java 17
- Maven 3.9+

## Run locally

```bash
mvn spring-boot:run
```

Create a product:

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Keyboard","price":1299.00}'
```

Update a product (replace `1` with its ID):

```bash
curl -X PUT http://localhost:8080/api/products/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Mechanical Keyboard","price":1599.50}'
```

## API endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/products` | List products |
| GET | `/api/products/{id}` | Retrieve one product |
| POST | `/api/products` | Create a product |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |

## Tests

Run the automated integration tests:

```bash
mvn --batch-mode test
```

Spring Boot integration tests use MockMvc and H2 to cover create, list, retrieve, update, delete, invalid product data, missing records, and price precision validation. GitHub Actions runs the Maven test suite on pushes and pull requests to `main`.

## Scope and limitations

This repository is a product-catalogue REST API demonstration. Customer authentication, shopping carts, order/checkout workflows, payment processing, a frontend, and production database configuration are not implemented. H2 is configured as an in-memory database; data is not retained across application restarts. No production deployment is claimed.

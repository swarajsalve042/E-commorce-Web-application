# E-Commerce Web Application

[![CI](https://github.com/swarajsalve042/E-commorce-Web-application/actions/workflows/ci.yml/badge.svg)](https://github.com/swarajsalve042/E-commorce-Web-application/actions/workflows/ci.yml)

Java 17 and Spring Boot REST API for a product catalogue.

## Features
- Create, list and retrieve products.
- Delete products by ID.
- Validates product name and positive price.
- Spring Data JPA persistence with an H2 in-memory database.
- REST API under `/api/products`.

## Requirements
Java 17 and Maven 3.9+.

## Run
`mvn spring-boot:run`

Create a product:
`curl -X POST http://localhost:8080/api/products -H "Content-Type: application/json" -d '{"name":"Keyboard","price":1299.00}'`

## Test
`mvn test`

## Scope
Product catalogue CRUD demo only. Cart, checkout, payments, customer authentication and production database configuration are not implemented.
# E-Commerce Web Application

[![ci](https://github.com/swarajsalve042/E-commorce-Web-application/actions/workflows/ci.yml/badge.svg)](https://github.com/swarajsalve042/E-commorce-Web-application/actions/workflows/ci.yml)

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

## Automated test coverage

The Spring Boot integration tests use MockMvc and an in-memory H2 database to verify product creation, listing, retrieval, deletion, not-found responses, and invalid name/price validation. Run with `mvn test`.

## Scope
Product catalogue CRUD demo only. Cart, checkout, payments, customer authentication and production database configuration are not implemented.

## Latest automated verification (25 November 2026)

- [Maven build and integration tests — successful GitHub Actions run](https://github.com/swarajsalve042/E-commorce-Web-application/actions/runs/45572937758)

These links document automated checks only; they do not imply production deployment or manual end-user acceptance testing.

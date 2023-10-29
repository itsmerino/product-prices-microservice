# Product Prices Microservice [![ci](https://github.com/itsmerino/product-prices-microservice/actions/workflows/maven.yml/badge.svg)](https://github.com/itsmerino/product-prices-microservice/actions/workflows/maven.yml)

Service that provides product prices through a REST endpoint.

The service has been implemented using a hexagonal architecture, an API First approach and using the ATDD methodology.

The service is tested through unit tests, integration tests and acceptance tests.

## Introduction

First, the API specification has been defined in OpenAPI format. 

Secondly, the scenarios to be tested have been defined through Gherkin and implemented through Cucumber (ATDD red phase). 

Thirdly, the functionalities of the service have been implemented through different TDD cycles until reaching the final result.

## Tech Stack

- Maven
- Java 17
- Spring Boot
- H2
- Lombok
- JUnit 5
- Mockito
- Cucumber

## API Reference

You can find the API specification in OpenAPI format here: [openapi.yaml](https://github.com/itsmerino/product-prices-microservice/blob/main/src/main/resources/openapi.yaml)

And the Postman collection for testing the API here: [product-prices.postman_collection](https://github.com/itsmerino/product-prices-microservice/blob/main/src/main/resources/product-prices.postman_collection)

## Run Locally

Clone the project

```bash
  git clone https://github.com/itsmerino/product-prices-microservice
```

Go to the project directory

```bash
  cd product-prices-microservice
```

Run the application

```bash
  ./mvnw spring-boot:run
```

## Running Tests

To run tests, run the following command

```bash
  ./mvnw test
```

## Author

- [Jorge Merino](https://www.github.com/itsmerino)

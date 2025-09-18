# Architecture Overview

This project follows **Clean Architecture / Hexagonal principles**:

## Layers

1. **Domain (`domain`)**
    - Core business entities: `BondEntity`, `StockEntity`, `UserEntity`.
    - Immutable data with Lombok Builder pattern.

2. **Application (`application`)**
    - Business logic and use cases:
        - `BondService` – computes bond-related metrics (e.g., YTM).
        - `StockService` – computes stock-related metrics (e.g., volatility).

3. **Adapter (`adapter`)**
    - Data input/output:
        - `BondRepository` – loads CSV & JSON for bonds.
        - `StockRepository` – persists and retrieves stock data from H2 DB.
        - `UserRepository` – H2 DB persistence for users.

4. **Controller (`controller`)**
    - REST API endpoints:
        - `BondController` – handles HTTP requests for bonds.
        - `StockController` – handles HTTP requests for stocks.
    - Returns `ResponseEntity` with appropriate status codes.

## Design Patterns

- **Factory:**
    - `BondFactory` creates `BondEntity` objects from CSV or JSON DTOs.
- **Builder:** Lombok `@Builder` for `BondEntity`, `StockEntity`, and `UserEntity`.
- **Strategy (extendable):** Risk metric calculations can be swapped or extended for bonds and stocks.

## Benefits

- Clear separation of domain logic from infrastructure.
- Easily extendable to support new asset classes (e.g., stocks, derivatives).
- Testable components for unit and integration tests.
- Consistent API and service layer design for multiple asset types.

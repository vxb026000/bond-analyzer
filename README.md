# Bond & Stock Portfolio Risk Analyzer

A Spring Boot application to manage bond and stock portfolios, calculate risk metrics, and provide a secure REST API.

## Features

- **Domain Model:** Bond, Stock, UserEntity with Lombok Builder pattern.
- **Data Sources:** Load bond data from CSV and JSON files. Stock data needs to be added via POST api
- **Risk Metrics:**
    - Bonds: Yield to Maturity (YTM); extendable for Duration/Modified Duration.
    - Stocks: Volatility calculation based on historical or current price data.
- **Persistence:** H2 in-memory database with Spring Data JPA for users and stocks.
- **Security:** Spring Security with HTTP Basic Auth; passwords stored as BCrypt hashes.
- **REST API:**
    - **Bonds**
        - `GET /api/bonds` → fetch all bonds
        - `GET /api/bonds/{isin}` → fetch a bond by ISIN
        - `GET /api/bonds/{isin}/ytm` → compute YTM for a bond
    - **Stocks**
        - `GET /api/stocks` → fetch all stocks
        - `GET /api/stocks/{ticker}` → fetch a stock by ticker symbol
        - `GET /api/stocks/{ticker}/volatility` → compute stock volatility
        - `POST /api/stocks` → Create new stock entity
- **Testing:** Unit and integration tests covering services, repository, factory, and controller.
- **Clean Architecture:** `domain`, `application`, `adapter`, `controller` layers for separation of concerns.

## Credentials (For test-only)

- **Username:** `admin`
- **Password:** `password` (BCrypt hashed in DB)
## Credentials (For test-only)

- **Username:** `admin`
- **Password:** `password` (BCrypt hashed in DB)

## Run
```bash
mvn spring-boot:run
```


## Run Tests
```bash
mvn test
```
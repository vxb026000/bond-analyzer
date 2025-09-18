# API Documentation

## Base URL
http://localhost:8080/api/

## Bonds Endpoints

### 1. GET /api/bonds
- Returns all bonds.
- **Responses:**
    - `200 OK` → list of bonds
    - `204 No Content` → if no bonds exist

### 2. GET /api/bonds/{isin}
- Returns a single bond by ISIN.
- **Responses:**
    - `200 OK` → bond details
    - `404 Not Found` → bond does not exist

### 3. GET /api/bonds/{isin}/ytm
- Returns the Yield to Maturity for a bond.
- **Responses:**
    - `200 OK` → numeric YTM
    - `404 Not Found` → bond does not exist

### Example Requests (local testing)
1. localhost:8080/api/bonds
2. localhost:8080/api/bonds/US6666666666
3. localhost:8080/api/bonds/US6666666666/ytm


## Stocks Endpoints

### 1. GET /api/stocks
- Returns all stocks.
- **Responses:**
    - `200 OK` → list of stocks
    - `204 No Content` → if no stocks exist

### 2. GET /api/stocks/{ticker}
- Returns a single stock by ticker symbol.
- **Responses:**
    - `200 OK` → stock details
    - `404 Not Found` → stock does not exist

### 3. GET /api/stocks/{ticker}/volatility
- Returns the volatility (standard deviation) of the stock’s historical prices.
- **Responses:**
    - `200 OK` → numeric volatility
    - `404 Not Found` → stock does not exist

### 4. POST /api/stocks
- Creates a new stock record.
- **Request Body Example:**
```json
{
  "ticker": "MSFT",
  "name": "Microsoft Corp",
  "historicalPrices": [300.0, 305.0, 310.0]
}
```
- **Responses:**
- `200 OK` → stock created successfully
- `400 Bad Request` → invalid or missing data
- `401 Unauthorized` → invalid credentials

### Example Requests (local testing)
1. localhost:8080/api/stocks
2. localhost:8080/api/stocks/AAPL
3. localhost:8080/api/stocks/AAPL/volatility
4. POST to localhost:8080/api/stocks with JSON body to add a stock


## Authentication

- HTTP Basic Auth required.
- Use header (in Postman):
  ```Authorization: Basic YWRtaW46cGFzc3dvcmQ```
- FYI - This is (Base64 of `admin:password`)

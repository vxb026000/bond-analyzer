# Data Loading

## CSV File

- Path: `src/main/resources/bonds.csv`
- Columns: isin,maturityDate,couponRate,faceValue,marketPrice,couponsPerYear


## JSON File

- Path: `src/main/resources/bonds.json`
- Example:
```json
[
  {
    "isin": "US5555555555",
    "maturityDate": "2035-12-31",
    "couponRate": 0.06,
    "faceValue": 1000,
    "marketPrice": 920,
    "couponsPerYear": 2
  },
  {
    "isin": "US6666666666",
    "maturityDate": "2036-12-31",
    "couponRate": 0.08,
    "faceValue": 1100,
    "marketPrice": 990,
    "couponsPerYear": 2
  }
]

```

## CSV File
- Path: `src/main/resources/bonds.csv`
- Example:
```
isin,maturityDate,couponRate,faceValue,marketPrice,couponsPerYear
US1234567890,2030-12-31,0.05,1000,950,2
US0987654321,2028-06-30,0.04,1000,980,1
```

## Stock creation sample data
```json
{
  "ticker": "MSFT",
  "name": "Microsoft Corporation",
  "historicalPrices": [300.0, 305.0, 302.5, 310.0, 308.0]
}
```
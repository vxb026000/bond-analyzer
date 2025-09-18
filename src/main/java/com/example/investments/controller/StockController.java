package com.example.investments.controller;


import com.example.investments.application.StockService;
import com.example.investments.domain.model.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<Collection<StockEntity>> getAllStocks() {
        return ResponseEntity.ok(stockService.getAllStocks());
    }

    @GetMapping("/{ticker}")
    public ResponseEntity<StockEntity> getStock(@PathVariable String ticker) {
        return stockService.getStockByTicker(ticker)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{ticker}/volatility")
    public ResponseEntity<Double> getVolatility(@PathVariable String ticker) {
        return stockService.getStockByTicker(ticker)
                .map(stock -> ResponseEntity.ok(stockService.computeVolatility(stock)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StockEntity> createStock(@RequestBody StockEntity stock) {
        return ResponseEntity.ok(stockService.createStock(
                stock.getTicker(),
                stock.getName(),
                stock.getHistoricalPrices()
        ));
    }
}

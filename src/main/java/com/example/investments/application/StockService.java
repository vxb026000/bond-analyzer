package com.example.investments.application;

import com.example.investments.adapter.persistence.StockRepository;
import com.example.investments.domain.model.StockEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final StockRepository stockRepository;

    public Collection<StockEntity> getAllStocks() {
        log.info("Fetching all stocks");
        return stockRepository.findAll();
    }

    @Cacheable("stocks")
    public Optional<StockEntity> getStockByTicker(String ticker) {
        log.info("Fetching stock with ticker: {}", ticker);
        return stockRepository.findByTicker(ticker);
    }

    public Double computeVolatility(StockEntity stock) {
        if (stock.getHistoricalPrices() == null || stock.getHistoricalPrices().isEmpty()) {
            return 0.0;
        }
        double mean = stock.getHistoricalPrices().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
        double variance = stock.getHistoricalPrices().stream()
                .mapToDouble(p -> Math.pow(p - mean, 2))
                .average()
                .orElse(0.0);
        return Math.sqrt(variance);
    }

    public StockEntity createStock(String ticker, String name, Collection<Double> historicalPrices) {
        StockEntity stock = StockEntity.builder()
                .ticker(ticker)
                .name(name)
                .historicalPrices(List.copyOf(historicalPrices))
                .build();
        return stockRepository.save(stock);
    }
}

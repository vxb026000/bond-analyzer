package com.example.investments.unit.application;

import com.example.investments.adapter.persistence.StockRepository;
import com.example.investments.application.StockService;
import com.example.investments.domain.model.StockEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.offset;
import static org.mockito.Mockito.*;

class StockServiceTest {

    private StockRepository stockRepository;
    private StockService stockService;

    @BeforeEach
    void setUp() {
        stockRepository = mock(StockRepository.class);
        stockService = new StockService(stockRepository);
    }

    @Test
    void testGetAllStocks() {
        stockService.getAllStocks();
        verify(stockRepository, times(1)).findAll();
    }

    @Test
    void testGetStockByTicker() {
        StockEntity stock = StockEntity.builder().ticker("AAPL").build();
        when(stockRepository.findByTicker("AAPL")).thenReturn(Optional.of(stock));

        Optional<StockEntity> result = stockService.getStockByTicker("AAPL");
        assertThat(result).isPresent();
        assertThat(result.get().getTicker()).isEqualTo("AAPL");
    }

    @Test
    void testComputeVolatility() {
        StockEntity stock = StockEntity.builder().historicalPrices(List.of(1.0, 2.0, 3.0)).build();
        Double volatility = stockService.computeVolatility(stock);
        assertThat(volatility).isCloseTo(0.816, offset(0.001));
    }
}

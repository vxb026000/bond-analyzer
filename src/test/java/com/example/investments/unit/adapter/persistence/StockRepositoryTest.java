package com.example.investments.unit.adapter.persistence;

import com.example.investments.adapter.persistence.StockRepository;
import com.example.investments.domain.model.StockEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    void testFindByTicker() {
        StockEntity stock = StockEntity.builder()
                .ticker("GOOG")
                .name("Google")
                .historicalPrices(List.of(1000.0, 1010.0))
                .build();
        stockRepository.save(stock);

        Optional<StockEntity> found = stockRepository.findByTicker("GOOG");
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Google");
    }
}

package com.example.investments.adapter.persistence;


import com.example.investments.domain.model.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Optional<StockEntity> findByTicker(String ticker);
}

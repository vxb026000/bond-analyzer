package com.example.investments.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "stocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String ticker;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "stock_prices", joinColumns = @JoinColumn(name = "stock_id"))
    @Column(name = "price")
    private List<Double> historicalPrices;
}

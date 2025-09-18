package com.example.investments.unit.application;

import com.example.investments.adapter.persistence.BondRepository;
import com.example.investments.application.BondService;
import com.example.investments.domain.model.BondEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BondServiceTest {

    private BondRepository bondRepository;
    private BondService bondService;

    @BeforeEach
    void setUp() {
        bondRepository = mock(BondRepository.class);
        bondService = new BondService(bondRepository);
    }

    @Test
    void testGetBondByIsin() {
        BondEntity bond = BondEntity.builder()
                .isin("US123")
                .maturityDate(LocalDate.now().plusYears(5))
                .couponRate(0.05)
                .faceValue(1000)
                .marketPrice(950)
                .couponsPerYear(2)
                .build();

        when(bondRepository.findByIsin("US123")).thenReturn(Optional.of(bond));

        Optional<BondEntity> result = bondService.getBondByIsin("US123");
        assertTrue(result.isPresent());
        assertEquals("US123", result.get().getIsin());
    }

    @Test
    void testComputeYtm() {
        BondEntity bond = BondEntity.builder()
                .isin("US123")
                .maturityDate(LocalDate.now().plusYears(5))
                .couponRate(0.05)
                .faceValue(1000)
                .marketPrice(950)
                .couponsPerYear(2)
                .build();

        double ytm = bondService.computeYtm(bond);
        assertTrue(ytm > 0);
    }
}

package com.example.investments.unit.domain.service;

import com.example.investments.adapter.persistence.BondDto;
import com.example.investments.domain.model.BondEntity;
import com.example.investments.domain.service.BondFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BondFactoryTest {

    @Test
    void testFromCsv() {
        BondEntity bond = BondFactory.fromCsv(
                "US1111111111",
                LocalDate.of(2030, 12, 31),
                0.05, 1000, 950, 2);

        assertEquals("US1111111111", bond.getIsin());
        assertEquals(1000, bond.getFaceValue());
        assertEquals(0.05, bond.getCouponRate());
    }

    @Test
    void testFromJson() {
        BondDto dto = BondDto.builder()
                .isin("US2222222222")
                .maturityDate(LocalDate.of(2035, 6, 30))
                .couponRate(0.04)
                .faceValue(1000)
                .marketPrice(980)
                .couponsPerYear(1)
                .build();

        BondEntity bond = BondFactory.fromJson(dto);

        assertEquals("US2222222222", bond.getIsin());
        assertEquals(980, bond.getMarketPrice());
        assertEquals(1, bond.getCouponsPerYear());
    }
}

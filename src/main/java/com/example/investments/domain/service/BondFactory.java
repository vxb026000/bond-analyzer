package com.example.investments.domain.service;

import com.example.investments.adapter.persistence.BondDto;
import com.example.investments.domain.model.BondEntity;

public class BondFactory {

    public static BondEntity fromCsv(String isin,
                                     java.time.LocalDate maturityDate,
                                     double couponRate,
                                     double faceValue,
                                     double marketPrice,
                                     int couponsPerYear) {
        return BondEntity.builder()
                .isin(isin)
                .maturityDate(maturityDate)
                .couponRate(couponRate)
                .faceValue(faceValue)
                .marketPrice(marketPrice)
                .couponsPerYear(couponsPerYear)
                .build();
    }

    public static BondEntity fromJson(BondDto dto) {
        return BondEntity.builder()
                .isin(dto.getIsin())
                .maturityDate(dto.getMaturityDate())
                .couponRate(dto.getCouponRate())
                .faceValue(dto.getFaceValue())
                .marketPrice(dto.getMarketPrice())
                .couponsPerYear(dto.getCouponsPerYear())
                .build();
    }
}

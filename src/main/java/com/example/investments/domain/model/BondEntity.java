package com.example.investments.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BondEntity {
    private String isin;
    private LocalDate maturityDate;
    private double couponRate;
    private double faceValue;
    private double marketPrice;
    private int couponsPerYear;
}

package com.example.investments.adapter.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BondDto {
    private String isin;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate maturityDate;

    private double couponRate;
    private double faceValue;
    private double marketPrice;
    private int couponsPerYear;
}

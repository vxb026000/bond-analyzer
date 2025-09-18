package com.example.investments.application;

import com.example.investments.adapter.persistence.BondRepository;
import com.example.investments.domain.model.BondEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BondService {
    private final BondRepository bondRepository;

    public BondService(BondRepository bondRepository) {
        this.bondRepository = bondRepository;
    }

    public Collection<BondEntity> getAllBonds() {
        return bondRepository.findAll();
    }

    public Optional<BondEntity> getBondByIsin(String isin) {
        return bondRepository.findByIsin(isin);
    }

    public double computeYtm(BondEntity bond) {
        double annualCoupon = bond.getCouponRate() * bond.getFaceValue();
        double years = bond.getMaturityDate().getYear() - java.time.LocalDate.now().getYear();
        return (annualCoupon + (bond.getFaceValue() - bond.getMarketPrice()) / years)
                / ((bond.getFaceValue() + bond.getMarketPrice()) / 2);
    }
}

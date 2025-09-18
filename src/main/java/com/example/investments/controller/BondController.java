package com.example.investments.controller;

import com.example.investments.application.BondService;
import com.example.investments.domain.model.BondEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/bonds")
public class BondController {

    private final BondService bondService;

    public BondController(BondService bondService) {
        this.bondService = bondService;
    }

    @GetMapping
    public ResponseEntity<Collection<BondEntity>> getAllBonds() {
        Collection<BondEntity> bonds = bondService.getAllBonds();
        if (bonds.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(bonds); // 200
    }

    @GetMapping("/{isin}")
    public ResponseEntity<BondEntity> getBondByIsin(@PathVariable String isin) {
        return bondService.getBondByIsin(isin)
                .map(ResponseEntity::ok) // 200
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404
    }

    @GetMapping("/{isin}/ytm")
    public ResponseEntity<Double> getBondYtm(@PathVariable String isin) {
        return bondService.getBondByIsin(isin)
                .map(bond -> ResponseEntity.ok(bondService.computeYtm(bond))) // 200
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404
    }
}

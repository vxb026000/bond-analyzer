package com.example.investments.unit.adapter.persistence;

import com.example.investments.adapter.persistence.BondRepository;
import com.example.investments.domain.model.BondEntity;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BondRepositoryTest {

    @Test
    void testLoadBondsFromCsvAndJson() {
        BondRepository repo = new BondRepository();
        repo.init(); // manually trigger @PostConstruct

        Collection<BondEntity> bonds = repo.findAll();
        assertFalse(bonds.isEmpty(), "Repository should load investments from resources");
    }

    @Test
    void testFindByIsin() {
        BondRepository repo = new BondRepository();
        repo.init();

        Optional<BondEntity> bond = repo.findByIsin("US1234567890"); // from investments.csv
        assertTrue(bond.isPresent());
        assertEquals("US1234567890", bond.get().getIsin());
    }

    @Test
    void testFindByIsinNotFound() {
        BondRepository repo = new BondRepository();
        repo.init();

        Optional<BondEntity> bond = repo.findByIsin("INVALID_ISIN");
        assertTrue(bond.isEmpty());
    }
}

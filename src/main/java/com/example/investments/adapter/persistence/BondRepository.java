package com.example.investments.adapter.persistence;

import com.example.investments.domain.model.BondEntity;
import com.example.investments.domain.service.BondFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Repository;


import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.*;


@Repository
public class BondRepository {

    public static final String BONDS_CSV = "/bonds.csv";
    public static final String BONDS_JSON = "/bonds.json";
    private final Map<String, BondEntity> bonds = new HashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        loadFromCsv();
        loadFromJson();
    }

    private void loadFromCsv() {
        try (var is = getClass().getResourceAsStream(BONDS_CSV)) {
            if (is == null) return;

            try (var reader = new InputStreamReader(is)) {
                CSVFormat format = CSVFormat.DEFAULT.builder()
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .build();

                try (CSVParser parser = CSVParser.parse(reader, format)) {
                    for (CSVRecord record : parser) {
                        String isin = record.get("isin");
                        LocalDate maturity = LocalDate.parse(record.get("maturityDate"));
                        double coupon = Double.parseDouble(record.get("couponRate"));
                        double face = Double.parseDouble(record.get("faceValue"));
                        double price = Double.parseDouble(record.get("marketPrice"));
                        int couponsPerYear = Integer.parseInt(record.get("couponsPerYear"));

                        BondEntity bond = BondFactory.fromCsv(isin, maturity, coupon, face, price, couponsPerYear);
                        bonds.put(isin, bond);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load investments from CSV", e);
        }
    }

    private void loadFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try (var is = getClass().getResourceAsStream("/bonds.json")) {
            if (is == null) return;

            List<BondDto> list = objectMapper.readValue(is, new TypeReference<>() {
            });
            for (BondDto dto : list) {
                BondEntity bond = BondFactory.fromJson(dto);
                bonds.put(dto.getIsin(), bond);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load investments from JSON", e);
        }
    }

    public Collection<BondEntity> findAll() {
        return bonds.values();
    }

    public Optional<BondEntity> findByIsin(String isin) {
        return Optional.ofNullable(bonds.get(isin));
    }
}

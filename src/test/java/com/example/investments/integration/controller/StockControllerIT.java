package com.example.investments.integration.controller;

import com.example.investments.adapter.persistence.StockRepository;
import com.example.investments.adapter.persistence.UserRepository;
import com.example.investments.domain.model.StockEntity;
import com.example.investments.domain.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StockControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        // Clean users and stocks before each test
        userRepository.deleteAll();
        stockRepository.deleteAll();

        // Insert admin user for HTTP Basic auth
        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRole("ADMIN");
        userRepository.save(admin);

        // Insert sample stock with historical prices
        StockEntity apple = StockEntity.builder()
                .ticker("AAPL")
                .name("Apple Inc.")
                .historicalPrices(List.of(150.0, 152.0, 149.0, 151.0, 153.0))
                .build();
        stockRepository.save(apple);
    }


    @Test
    void testGetAllStocksUnauthorized() throws Exception {
        mockMvc.perform(get("/api/stocks"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetAllStocksWithAuth() throws Exception {
        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString("admin:password".getBytes());

        mockMvc.perform(get("/api/stocks")
                        .header("Authorization", basicAuth))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetStockByTickerWithAuth() throws Exception {
        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString("admin:password".getBytes());

        mockMvc.perform(get("/api/stocks/AAPL")
                        .header("Authorization", basicAuth))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetVolatilityWithAuth() throws Exception {
        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString("admin:password".getBytes());

        mockMvc.perform(get("/api/stocks/AAPL/volatility")
                        .header("Authorization", basicAuth))
                .andExpect(status().isOk());
    }
}

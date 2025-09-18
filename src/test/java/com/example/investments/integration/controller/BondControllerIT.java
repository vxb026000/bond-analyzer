package com.example.investments.integration.controller;

import com.example.investments.adapter.persistence.UserRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BondControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        // Clean users before each test
        userRepository.deleteAll();

        // Insert admin user for HTTP Basic auth
        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("password")); // match what test sends
        admin.setRole("ADMIN");
        userRepository.save(admin);
    }

    @Test
    void testGetAllBondsUnauthorized() throws Exception {
        mockMvc.perform(get("/api/bonds"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetAllBondsWithAuth() throws Exception {
        // Generate Basic Auth header: admin:password
        String basicAuth = "Basic " + Base64.getEncoder()
                .encodeToString("admin:password".getBytes());

        mockMvc.perform(get("/api/bonds")
                        .header("Authorization", basicAuth))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}

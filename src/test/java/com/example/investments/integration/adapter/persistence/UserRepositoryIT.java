package com.example.investments.integration.adapter.persistence;

import com.example.investments.domain.model.UserEntity;
import com.example.investments.adapter.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;


    @Test
    void testFindByUsername() {
        UserEntity user = UserEntity.builder()
                .username("testuser")
                .password("hashedpwd")
                .role("ROLE_USER")
                .build();
        userRepository.save(user);

        Optional<UserEntity> found = userRepository.findByUsername("testuser");
        assertTrue(found.isPresent());
        assertEquals("testuser", found.get().getUsername());
    }

    @Test
    void testFindByUsernameNotFound() {
        Optional<UserEntity> found = userRepository.findByUsername("ghost");
        assertTrue(found.isEmpty());
    }
}

package com.example.investments.config;

import com.example.investments.adapter.persistence.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = Map.of(
                idForEncode, new BCryptPasswordEncoder()
        );
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            System.out.println(">> looking up user: " + username);
            return userRepository.findByUsername(username)
                    .map(u -> User.builder()
                            .username(u.getUsername())
                            .password(u.getPassword())
                            .roles(u.getRole())
                            .build())
                    .orElseThrow(() -> new RuntimeException("User not found: " + username));
        };
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // disable CSRF for API & H2
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // allow H2 console frames
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()  // allow H2 console
                        .requestMatchers("/api/bonds/**").hasRole("ADMIN")
                        .requestMatchers("/api/stocks/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

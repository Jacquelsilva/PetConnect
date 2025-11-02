package com.example.petconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desativa o CSRF (útil para testes com Postman)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // libera os endpoints de autenticação
                .anyRequest().permitAll() // libera todo o resto também (só para teste)
            )
            .formLogin(login -> login.disable()) // desativa formulário padrão do Spring
            .httpBasic(basic -> basic.disable()); // desativa autenticação HTTP básica

        return http.build();
    }
}

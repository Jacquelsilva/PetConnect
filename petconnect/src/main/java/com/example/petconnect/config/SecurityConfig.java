package com.example.petconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

    // 🔹 Bean para criptografar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🔹 Configuração de segurança
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // desativa CSRF (útil em testes)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/uploads/**").permitAll() // libera autenticação e imagens
                .anyRequest().permitAll() // libera todo o resto (ajuste depois se quiser restringir)
            )
            .formLogin(login -> login.disable()) // desativa login padrão
            .httpBasic(basic -> basic.disable()); // desativa autenticação HTTP básica

        return http.build();
    }

    // 🔹 Libera acesso à pasta de uploads de imagens
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // /uploads/** → arquivos salvos na pasta local "uploads/"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}

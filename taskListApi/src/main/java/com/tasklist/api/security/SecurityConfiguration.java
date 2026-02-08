package com.tasklist.api.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.tasklist.api.security.jwt.JwtAuthFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter authFilter) throws Exception {
        http.csrf((csrf) -> csrf
                .disable().authorizeHttpRequests(
                        auth -> auth.requestMatchers("/api/v1/auth/login", "/api/v1/auth/register", "/api/v1/oauth2/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/api/v1/oauth2/success"))
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\":\"Unauthorized or invalid token\"}");
                        }))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class));
        return http.build();
    }

}

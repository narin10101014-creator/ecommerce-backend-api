package com.narin.ecommerce.config;

import com.narin.ecommerce.constants.ApiPaths;
import com.narin.ecommerce.constants.Roles;
import com.narin.ecommerce.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                ApiPaths.SWAGGER_UI,
                                ApiPaths.SWAGGER_HTML,
                                ApiPaths.API_DOCS).permitAll()
                        // Auth
                        .requestMatchers(ApiPaths.AUTH).permitAll()

                        // Category
                        .requestMatchers(HttpMethod.GET, ApiPaths.CATEGORIES).permitAll()
                        .requestMatchers(ApiPaths.CATEGORIES).hasRole(Roles.ADMIN)

                        // Product
                        .requestMatchers(HttpMethod.GET, ApiPaths.PRODUCTS).permitAll()
                        .requestMatchers(ApiPaths.PRODUCTS).hasRole(Roles.ADMIN)

                        // User
                        .requestMatchers(ApiPaths.USERS).authenticated()

                        .anyRequest().authenticated()
                );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

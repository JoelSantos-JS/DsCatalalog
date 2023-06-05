package com.devsuperior.DsCatalog.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // TODO: Proper authentication.
        http.csrf().disable().cors().disable();

        http.authorizeHttpRequests().requestMatchers("/products", "/user", "/categories")
                .permitAll().anyRequest()
                .authenticated();
        return http.build();
    }

}

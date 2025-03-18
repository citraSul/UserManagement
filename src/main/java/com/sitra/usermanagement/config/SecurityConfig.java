package com.sitra.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
/**
 * Configuration class for securing API endpoints using OAuth2 and JWT authentication.
 * <p>
 * This class ensures that API requests are authenticated using OAuth2 JWT tokens
 * and that appropriate authorization rules are applied based on user scopes.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
@Configuration
public class SecurityConfig {
    /**
     * Configures the security filter chain for API endpoints.
     * <p>
     * - Only applies security configurations to URLs starting with <code>/api/**</code>.<br>
     * - Requires appropriate OAuth2 scopes (`SCOPE_read`, `SCOPE_write`) to access user-related endpoints.<br>
     * - Uses JWT authentication to validate tokens.
     * </p>
     *
     * @param http The {@link HttpSecurity} object to configure security settings.
     * @return A {@link SecurityFilterChain} object defining security rules for API endpoints.
     * @throws Exception If an error occurs while configuring security.
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**") //Ensures this applies ONLY to API endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users").hasAuthority("SCOPE_write") // Allow write scope
                        .requestMatchers("/api/users/**").hasAnyAuthority("SCOPE_read", "SCOPE_write") // Allow read & write scopes
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
    /**
     * Configures the JWT Decoder for OAuth2 authentication.
     * <p>
     * - This decoder fetches public keys from the JWKS endpoint to validate JWT signatures.<br>
     * - Used by Spring Security to authenticate and authorize users based on JWT tokens.<br>
     * - Ensures secure and stateless authentication.
     * </p>
     *
     * @return A {@link JwtDecoder} that validates JWT tokens using the provided JWKS URI.
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/oauth2/jwks").build();
    }
}

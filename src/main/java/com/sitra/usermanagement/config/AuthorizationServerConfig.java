package com.sitra.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
/**
 * Configuration class for setting up the OAuth2 Authorization Server.
 * <p>
 * This configuration ensures that OAuth2 authorization and token endpoints
 * are properly secured and accessible. It also defines the authorization and token endpoints.
 * </p>
 *
 * @author sitra
 * @version 1.0
 */
@Configuration
public class AuthorizationServerConfig {
    /**
     * Configures the security filter chain for the Authorization Server.
     * <p>
     * This method applies security configurations to ensure that only authenticated requests
     * can access OAuth2-related endpoints. It also disables CSRF protection for OAuth2 endpoints.
     * </p>
     *
     * @param http The {@link HttpSecurity} object to configure security settings.
     * @return A {@link SecurityFilterChain} object defining the security configuration.
     * @throws Exception If an error occurs while configuring security.
     */
    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        http
                .securityMatcher("/oauth2/**") //  Ensures this applies ONLY to OAuth2 endpoints
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/oauth2/**"))
                .apply(authorizationServerConfigurer); // Apply OAuth2 Authorization Server Configuration

        return http.build();
    }
    /**
     * Configures settings for the OAuth2 Authorization Server.
     * <p>
     * This method defines the authorization and token endpoints used in the OAuth2 flow.
     * </p>
     *
     * @return An {@link AuthorizationServerSettings} object defining OAuth2 endpoints.
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
                .authorizationEndpoint("/oauth2/authorize")
                .tokenEndpoint("/oauth2/token")
                .build();
    }

}



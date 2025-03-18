package com.sitra.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import java.util.UUID;
/**
 * Configuration class for registering OAuth2 clients.
 * <p>
 * This class defines OAuth2 clients that can interact with the authorization server.
 * It specifies the client credentials, supported grant types, redirect URIs, and scopes.
 * </p>
 *
 * @author sitra
 * @version 1.0
 */
@Configuration
public class OAuth2ClientConfig {
    /**
     * Registers an OAuth2 client with the authorization server.
     * <p>
     * - Defines a client with ID <code>my-client-id</code> and a plain-text secret (NoOp for testing).<br>
     * - Supports multiple grant types: Client Credentials, Authorization Code, and Refresh Token.<br>
     * - Sets the redirect URI for the Authorization Code flow.<br>
     * - Defines the permitted scopes for API access.
     * </p>
     *
     * @return A {@link RegisteredClientRepository} storing the OAuth2 client configuration.
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient client = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("my-client-id") // 🔹 Make sure this matches Postman
                .clientSecret("{noop}my-client-secret") // 🔹 Ensure this is NoOp for testing
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // 🔹 Use correct grant type
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:8080/login/oauth2/code/my-client")
                .scope("read")
                .scope("write")
                .build();

        return new InMemoryRegisteredClientRepository(client);
    }
}

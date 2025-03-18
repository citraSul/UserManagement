/*
package com.sitra.usermanagement.audit;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

*/
/**
 * Retrieves the currently authenticated user for auditing purposes.
 *//*

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    */
/**
     * Fetches the logged-in user's ID or username for audit fields.
     *
     * @return The currently authenticated user ID or username.
     *//*

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("am inside ********************");
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("No authenticated user found");
            return Optional.empty();
        }
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String userId = jwt.getClaimAsString("sub"); // "sub" usually contains user ID
            System.out.println("Current Auditor: " + userId);
            return Optional.ofNullable(userId);
        }

        System.out.println("Current Auditor (fallback): " + authentication.getName());
        return Optional.ofNullable(authentication.getName()); // Default to username
    }
}

*/

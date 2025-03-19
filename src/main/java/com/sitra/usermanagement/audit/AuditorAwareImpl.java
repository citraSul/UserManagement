package com.sitra.usermanagement.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

private static final Logger LOGGER = LoggerFactory.getLogger(AuditorAwareImpl.class);

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LOGGER.info("Checking authentication in getCurrentAuditor...");

        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("No authenticated user found or user is not authenticated!");
            return Optional.empty();
        }

        System.out.println("Authentication found: " + authentication);

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            String userId = jwt.getClaimAsString("sub"); // Ensure the claim exists
            if (userId == null || userId.isEmpty()) {
                LOGGER.info(" JWT does not contain 'sub' claim!");
                return Optional.empty();
            }
            LOGGER.info(" Current Auditor (from JWT 'sub' claim): " + userId);
            return Optional.of(userId);
        }

        LOGGER.info(" Current Auditor (fallback - authentication name): " + authentication.getName());
        return Optional.ofNullable(authentication.getName()); // Default to username
    }
}

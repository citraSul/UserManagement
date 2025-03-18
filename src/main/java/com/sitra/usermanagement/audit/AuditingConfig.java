/*
package com.sitra.usermanagement.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

*/
/**
 * Configuration class to enable JPA Auditing.
 * This allows automatic population of audit fields.
 *//*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingConfig {

    */
/**
     * Defines the {@link AuditorAware} bean to fetch the currently authenticated user.
     *
     * @return The {@link AuditorAware} implementation.
     *//*

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl(); // 🔹 Fix: Register the AuditorAware bean
    }
}
*/

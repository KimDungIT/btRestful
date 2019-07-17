package com.example.btrestful1.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "createAuditorProvider")
public class AuditConfig {

    @Bean
    public AuditorAware<String> createAuditorProvider()
    {
        return new SecurityAuditor();
    }

    public static class SecurityAuditor implements AuditorAware<String>
    {

        @Override
        public Optional<String> getCurrentAuditor() {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null)
                return Optional.of("anonymous");

            String username = auth.getName();
            return Optional.of(username);
        }
    }

}

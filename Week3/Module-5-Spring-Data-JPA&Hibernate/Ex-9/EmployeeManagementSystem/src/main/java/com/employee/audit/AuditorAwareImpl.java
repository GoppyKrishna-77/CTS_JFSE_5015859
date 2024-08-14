package com.employee.audit;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.data.domain.AuditorAware;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("admin");
    }
}

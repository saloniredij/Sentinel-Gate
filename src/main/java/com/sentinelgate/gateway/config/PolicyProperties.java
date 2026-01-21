package com.sentinelgate.gateway.config;


import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Validated
@ConfigurationProperties(prefix = "policy")
public class PolicyProperties {

    @NotEmpty(message = "policy.allowed-models must not be empty")
    private List<String> allowedModels = new ArrayList<>();

    public List<String> getAllowedModels() {
        return allowedModels;
    }

    public void setAllowedModels(List<String> allowedModels) {
        this.allowedModels = allowedModels;
    }
    
}

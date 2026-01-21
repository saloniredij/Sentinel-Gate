package com.sentinelgate.gateway.policy;

import com.sentinelgate.gateway.config.PolicyProperties;
import com.sentinelgate.gateway.dto.ChatCompletionsRequest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PolicyEngine {

    private final PolicyProperties policyProperties;

    public PolicyEngine(PolicyProperties policyProperties){
        this.policyProperties = policyProperties;
    }

    //check the model and throw exception if the model isn't present in ALLOWED_MODELS
    public void enforce(ChatCompletionsRequest request) {
        if (request.model() == null || request.model().isBlank()){
            System.out.println(request.model());
            throw new PolicyViolationException("Model is required.");
        }

        Set<String> allowed = new HashSet<>(policyProperties.getAllowedModels());
        if (!allowed.contains(request.model())){
            throw new PolicyViolationException("Model not allowed: "+ request.model());
        }
    }
}

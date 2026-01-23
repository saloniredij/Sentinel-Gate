package com.sentinelgate.gateway.policy;

import com.sentinelgate.gateway.api.ChatCompletionsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sentinelgate.gateway.config.PolicyProperties;
import com.sentinelgate.gateway.dto.ChatCompletionsRequest;
import com.sentinelgate.gateway.error.PolicyViolationException;

@Component
public class PolicyEngine {

    private final PolicyProperties policyProperties;
    private static final Logger log = LoggerFactory.getLogger(PolicyEngine.class);


    public PolicyEngine(PolicyProperties policyProperties){
        this.policyProperties = policyProperties;
    }

    //check the model and throw exception if the model isn't present in ALLOWED_MODELS
    public void enforce(ChatCompletionsRequest request) {
        log.info("Policy check model={}", request.model());
        if (request.model() == null || request.model().isBlank()){
            System.out.println(request.model());
            throw new PolicyViolationException("Model is required.");
        }

//        Set<String> allowed = new HashSet<>(policyProperties.getAllowedModels());
        if (!policyProperties.getAllowedModels().contains(request.model())){
            throw new PolicyViolationException("Model not allowed: "+ request.model());
        }
    }
}

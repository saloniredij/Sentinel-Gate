package com.sentinelgate.gateway.policy;

import com.sentinelgate.gateway.dto.ChatCompletionsRequest;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PolicyEngine {

    private static final Set<String> ALLOWED_MODELS = Set.of(
            "demo-model",
            "gpt-4.1-mini",
            "gemini-flash-3.0"
    );

    //check the model and throw exception if the model isn't present in ALLOWED_MODELS
    public void enforce(ChatCompletionsRequest request) {
        if (!ALLOWED_MODELS.contains(request.model())){
            throw new PolicyViolationException("Model not allowed: "+ request.model());
        }
    }
}

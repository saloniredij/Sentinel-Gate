package com.sentinelgate.gateway.api;


import com.sentinelgate.gateway.dto.ChatChoice;
import com.sentinelgate.gateway.dto.ChatCompletionsRequest;
import com.sentinelgate.gateway.dto.ChatCompletionsResponse;
import com.sentinelgate.gateway.dto.ChatMessage;
import com.sentinelgate.gateway.provider.ProviderClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
public class ChatCompletionsController {

    private final ProviderClient providerClient;
    public ChatCompletionsController (ProviderClient providerClient){
        this.providerClient = providerClient;
    }

    @PostMapping("/v1/chat/completions")
    public Mono<ChatCompletionsResponse> createCompletion(@RequestBody ChatCompletionsRequest req) {

        return providerClient.chatCompletions(req);

    }
}

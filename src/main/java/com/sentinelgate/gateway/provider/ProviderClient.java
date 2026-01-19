package com.sentinelgate.gateway.provider;

import com.sentinelgate.gateway.dto.ChatCompletionsRequest;
import com.sentinelgate.gateway.dto.ChatCompletionsResponse;
import reactor.core.publisher.Mono;

public interface ProviderClient {

    Mono<ChatCompletionsResponse> chatCompletions(ChatCompletionsRequest request);
}

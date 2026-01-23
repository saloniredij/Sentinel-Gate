package com.sentinelgate.gateway.provider;


import com.sentinelgate.gateway.dto.ChatCompletionsRequest;
import com.sentinelgate.gateway.dto.ChatCompletionsResponse;
import com.sentinelgate.gateway.error.PolicyViolationException;
import com.sentinelgate.gateway.error.ProviderTimeoutException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Component
public class HttpProviderClient implements ProviderClient{
    private final WebClient providerWebClient;

    public HttpProviderClient(WebClient providerWebClient) {
        this.providerWebClient = providerWebClient;
    }

    @Override
    public Mono<ChatCompletionsResponse> chatCompletions(ChatCompletionsRequest request){

        return providerWebClient.post()
                .uri("/v1/chat/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatCompletionsResponse.class)
                .timeout(Duration.ofSeconds(12)) //max wait
                .onErrorMap(TimeoutException.class, t -> new ProviderTimeoutException("Provider Timeout"))
                .retryWhen(Retry.backoff(2,Duration.ofMillis(500))
                        .filter(this::isRetryable)
                        .onRetryExhaustedThrow((spec,signal) -> signal.failure())
                );


    }

    private boolean isRetryable(Throwable t){

        // never retry policy errors and own timeout exception
        if(t instanceof ProviderTimeoutException || t instanceof PolicyViolationException) return false;
        return true; //temporary -> remove later
    }


}

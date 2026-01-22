package com.sentinelgate.gateway.provider;


import com.sentinelgate.gateway.dto.ChatCompletionsRequest;
import com.sentinelgate.gateway.dto.ChatCompletionsResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
                .bodyToMono(ChatCompletionsResponse.class);
    }


}

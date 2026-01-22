package com.sentinelgate.gateway.api;


import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.sentinelgate.gateway.dto.ChatCompletionsResponse;
import com.sentinelgate.gateway.provider.ProviderClient;

import reactor.core.publisher.Mono;

@SpringBootTest(properties = {"policy.allowed-models= demo-model, gpt-4.1-mini,gemini-flash-3.0"})
@AutoConfigureWebTestClient
class ChatCompletionsControllerPolicyTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private ProviderClient providerClient;

    @Test
    void allowedModel_passesPolicy_andCallsProvider(){
        Mockito.when(providerClient.chatCompletions(any())).thenReturn(Mono.just(Mockito.mock(ChatCompletionsResponse.class)));

        webTestClient.post()
                .uri("/v1/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                        "model": "gpt-4.1-mini",
                        "messages": [{"role" : "user", "content": "Hello"}]
                        }""")
                .exchange()
                .expectStatus().is2xxSuccessful();
        Mockito.verify(providerClient).chatCompletions(any());

    }

    @Test
    void disallowedModel_isRejected_andDoesNotCallProvider(){
        webTestClient.post()
                .uri("/v1/chat/completions")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                        "model": "gpt-3.5-turbo",
                        "messages": [{"role" : "user", "content": "Hello"}]
                        }""")
                .exchange()
                .expectStatus().isForbidden();
        Mockito.verify(providerClient, Mockito.never()).chatCompletions(any());

    }




}

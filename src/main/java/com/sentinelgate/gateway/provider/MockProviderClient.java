package com.sentinelgate.gateway.provider;

import com.sentinelgate.gateway.dto.ChatChoice;
import com.sentinelgate.gateway.dto.ChatCompletionsRequest;
import com.sentinelgate.gateway.dto.ChatCompletionsResponse;
import com.sentinelgate.gateway.dto.ChatMessage;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;


@Component
public class MockProviderClient implements ProviderClient{

    @Override
    public Mono<ChatCompletionsResponse> chatCompletions(ChatCompletionsRequest req) {
        var userLastMessage = (req.messages() != null && !req.messages().isEmpty()) ?
                req.messages().get(req.messages().size() - 1).content() : "";

        var assistantMessage = new ChatMessage(
                "assistant", "Mock provider reply from gateway. You said : " +userLastMessage);

        var response = new ChatCompletionsResponse("chatcmpl_" + UUID.randomUUID(),
                req.model() == null ? "unknown" : req.model(),
                List.of(new ChatChoice(0, assistantMessage)));

        return Mono.just(response);

    }
}

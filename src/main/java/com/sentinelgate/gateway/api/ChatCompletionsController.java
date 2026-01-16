package com.sentinelgate.gateway.api;


import com.sentinelgate.gateway.dto.ChatChoice;
import com.sentinelgate.gateway.dto.ChatCompletionsRequest;
import com.sentinelgate.gateway.dto.ChatCompletionsResponse;
import com.sentinelgate.gateway.dto.ChatMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
public class ChatCompletionsController {

    @PostMapping("/v1/chat/completions")
    public Mono<ChatCompletionsResponse> createCompletion(@RequestBody ChatCompletionsRequest req) {

        //temporarily mocking the response
        var userLastMessage = (req.messages() != null && !req.messages().isEmpty()) ?
                req.messages().get(req.messages().size() - 1).content() : "";

        var assistantMessage = new ChatMessage(
                "assistant", "Mock reply from gateway. You said : " +userLastMessage);

        var response = new ChatCompletionsResponse("chatcmpl_" + UUID.randomUUID(),
                req.model() == null ? "unknown" : req.model(),
                List.of(new ChatChoice(0, assistantMessage)));

        return Mono.just(response);
    }
}

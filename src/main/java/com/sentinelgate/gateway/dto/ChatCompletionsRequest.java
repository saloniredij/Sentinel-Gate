package com.sentinelgate.gateway.dto;

import java.util.List;

public record ChatCompletionsRequest(String model, List<ChatMessage> messages) {
}

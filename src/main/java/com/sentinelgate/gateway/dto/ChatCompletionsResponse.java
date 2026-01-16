package com.sentinelgate.gateway.dto;
import java.util.List;

public record ChatCompletionsResponse(String id, String model, List<ChatChoice> choices) {
}

package com.sentinelgate.gateway.error;

public class ProviderRateLimitedException extends RuntimeException {
    public ProviderRateLimitedException(String message){
        super(message);
    }
}

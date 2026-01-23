package com.sentinelgate.gateway.error;

public class ProviderUpstreamException extends RuntimeException{
    public ProviderUpstreamException(String message){
        super(message);
    }
}

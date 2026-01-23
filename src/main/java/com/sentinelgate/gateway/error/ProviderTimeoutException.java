package com.sentinelgate.gateway.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProviderTimeoutException extends RuntimeException {

    public ProviderTimeoutException(String message){
        super("PROVIDER_TIMEOUT");
    }
}

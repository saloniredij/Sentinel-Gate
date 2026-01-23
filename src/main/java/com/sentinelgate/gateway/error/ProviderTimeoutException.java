package com.sentinelgate.gateway.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProviderTimeoutException extends ResponseStatusException {

    public ProviderTimeoutException(String message){
        super(HttpStatus.GATEWAY_TIMEOUT,message);
    }
}

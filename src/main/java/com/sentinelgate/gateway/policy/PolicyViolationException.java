package com.sentinelgate.gateway.policy;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PolicyViolationException extends ResponseStatusException {


    //use ResponseStatusException to throw exception
    public PolicyViolationException(String message) {
        super(HttpStatus.FORBIDDEN, message); // spring throws 403 forbidden status
    }
}

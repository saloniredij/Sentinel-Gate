package com.sentinelgate.gateway.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PolicyViolationException.class)
    public ResponseEntity<ApiError> handlePolicyViolation(PolicyViolationException ex){
        var body = new ApiError("POLICY_VIOLATION", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }

    @ExceptionHandler(ProviderTimeoutException.class)
    public ResponseEntity<ApiError> handleProviderTimeOut(ProviderTimeoutException ex){
        var body = new ApiError("PROVIDER_TIMEOUT", ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }

}

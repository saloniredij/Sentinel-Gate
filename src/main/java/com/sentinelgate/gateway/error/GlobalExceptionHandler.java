package com.sentinelgate.gateway.error;

import com.sentinelgate.gateway.policy.PolicyViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PolicyViolationException.class)
    public ResponseEntity<ApiError> handlePolicyViolation(PolicyViolationException ex){
        var body = new ApiError("POLICY_VIOLATION", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }

}

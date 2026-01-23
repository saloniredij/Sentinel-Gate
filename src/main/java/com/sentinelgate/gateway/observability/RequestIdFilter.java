package com.sentinelgate.gateway.observability;


import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class RequestIdFilter implements WebFilter {

    public static final String REQ_ID_HEADER = "X-Request-Id";
    public static final String MDC_KEY = "requestId";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain){
        String requestId = exchange.getRequest().getHeaders().getFirst(REQ_ID_HEADER);
        if (requestId ==null || requestId.isBlank()){
            requestId = UUID.randomUUID().toString();
        }

        // add to response header to make it visible to the client
        exchange.getResponse().getHeaders().add(REQ_ID_HEADER,requestId);

        // put to MDC so logs can include it
        MDC.put(MDC_KEY,requestId);

        return chain.filter(exchange).doFinally(signalType -> MDC.remove(MDC_KEY));
    }
}

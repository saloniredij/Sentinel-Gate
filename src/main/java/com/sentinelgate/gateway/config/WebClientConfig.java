package com.sentinelgate.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.http.HttpHeaders;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient providerWebClient(ProviderProperties props) {
        System.out.println("Provider baseUrl = [" + props.getBaseUrl() + "]");

        return WebClient.builder()
                .baseUrl(props.getBaseUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + props.getApiKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}

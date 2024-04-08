package com.maxtorgroup.democonsultas.infrastructure.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
    private String pathPattern;
    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;
    @Value("${cors.allowed-methods}")
    private String[] allowedMethods;
    @Value("${cors.allowed-headers}")
    private String[] allowedHeaders;
    private Boolean allowCredentials;
}

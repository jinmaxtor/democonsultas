package com.maxtorgroup.democonsultas.api.configuration;

import com.maxtorgroup.democonsultas.infrastructure.configuration.CorsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfigurer implements WebMvcConfigurer {
    private final CorsProperties properties;

    public CorsConfigurer(CorsProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(properties.getPathPattern())
                .allowedOrigins(properties.getAllowedOrigins())
                .allowedMethods(properties.getAllowedMethods())
                .allowedHeaders(properties.getAllowedHeaders())
                .allowCredentials(properties.getAllowCredentials());
    }
}

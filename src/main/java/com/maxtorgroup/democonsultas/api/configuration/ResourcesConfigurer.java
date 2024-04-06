package com.maxtorgroup.democonsultas.api.configuration;

import com.maxtorgroup.democonsultas.infrastructure.configuration.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class ResourcesConfigurer implements WebMvcConfigurer {

    private final StorageProperties properties;

    public ResourcesConfigurer(StorageProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler(properties.getBaseUrl() + "/**")
                .addResourceLocations("file:" + properties.getLocation() + "/");
    }
}

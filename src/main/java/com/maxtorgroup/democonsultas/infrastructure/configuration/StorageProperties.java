package com.maxtorgroup.democonsultas.infrastructure.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private String location = "uploads";
    private String baseUrl = "files";
}

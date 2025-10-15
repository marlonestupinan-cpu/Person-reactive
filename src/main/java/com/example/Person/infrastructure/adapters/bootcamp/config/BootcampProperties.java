package com.example.Person.infrastructure.adapters.bootcamp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("bootcamp-endpoint")
public class BootcampProperties {
    private String baseUrl;
    private String timeout;
}

package com.example.Person.infrastructure.adapters.report.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("report-endpoint")
public class ReportProperties {
    private String baseUrl;
    private String timeout;
}

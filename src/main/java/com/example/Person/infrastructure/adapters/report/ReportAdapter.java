package com.example.Person.infrastructure.adapters.report;

import com.example.Person.domain.model.Report;
import com.example.Person.domain.spi.IReportGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ReportAdapter implements IReportGateway {
    private final WebClient webClient;

    public ReportAdapter(@Qualifier("reportWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<String> sendReport(Report report) {
        return webClient
                .post()
                .bodyValue(report)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> log.info("Received API response: {}", response));
    }
}

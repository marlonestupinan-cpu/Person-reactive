package com.example.Person.domain.spi;

import com.example.Person.domain.model.Report;
import reactor.core.publisher.Mono;

public interface IReportGateway {
     Mono<String> sendReport(Report report);
}

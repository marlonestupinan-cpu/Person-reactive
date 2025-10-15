package com.example.Person.infrastructure.adapters.bootcamp;

import com.example.Person.domain.model.Bootcamp;
import com.example.Person.domain.spi.IBootcampGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BootcampAdapter implements IBootcampGateway {
    private final WebClient webClient;

    @Override
    public Flux<Bootcamp> getBootcampsById(List<Long> bootcampsId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getAll")
                        .queryParam("ids", bootcampsId)
                        .build()
                )
                .retrieve()
                .bodyToFlux(Bootcamp.class);
    }
}

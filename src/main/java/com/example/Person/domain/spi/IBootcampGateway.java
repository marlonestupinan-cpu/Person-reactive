package com.example.Person.domain.spi;

import com.example.Person.domain.model.Bootcamp;
import reactor.core.publisher.Flux;

import java.util.List;

public interface IBootcampGateway {
    Flux<Bootcamp> getBootcampsById(List<Long> bootcampsId);
}

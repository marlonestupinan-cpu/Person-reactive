package com.example.Person.domain.api;

import com.example.Person.domain.model.Person;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonServicePort {
    Mono<Person> registerPerson(Person person);

    Mono<Long> getBestBootcamp();

    Flux<Person> getPeopleFromBootcamp(Long id);
}

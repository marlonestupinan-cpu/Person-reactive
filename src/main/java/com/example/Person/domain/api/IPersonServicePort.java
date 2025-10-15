package com.example.Person.domain.api;

import com.example.Person.domain.model.Person;
import reactor.core.publisher.Mono;

public interface IPersonServicePort {
    Mono<Person> registerPerson(Person person);
}

package com.example.Person.domain.spi;

import com.example.Person.domain.model.Bootcamp;
import com.example.Person.domain.model.Person;
import com.example.Person.infrastructure.adapters.mysqladapter.entity.BootcampPersonEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonPersistencePort {
    Mono<Person> save(Person person);

    Flux<BootcampPersonEntity> saveBootcampPerson(Person person, Flux<Bootcamp> bootcamps);
}

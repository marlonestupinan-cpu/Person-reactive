package com.example.Person.infrastructure.adapters.mysqladapter.repository;

import com.example.Person.infrastructure.adapters.mysqladapter.entity.BootcampPersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IBootcampPersonRepository extends ReactiveCrudRepository<BootcampPersonEntity, Long> {
    Mono<Integer> countByIdBootcamp(Long id);
}

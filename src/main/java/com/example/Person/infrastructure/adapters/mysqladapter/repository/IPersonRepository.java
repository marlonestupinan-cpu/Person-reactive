package com.example.Person.infrastructure.adapters.mysqladapter.repository;

import com.example.Person.infrastructure.adapters.mysqladapter.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IPersonRepository extends ReactiveCrudRepository<PersonEntity, Long> {
}

package com.example.Person.infrastructure.adapters.mysqladapter.repository;

import com.example.Person.infrastructure.adapters.mysqladapter.entity.BootcampPersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IBootcampPersonRepository extends ReactiveCrudRepository<BootcampPersonEntity, Long> {
}

package com.example.Person.infrastructure.adapters.mysqladapter.repository;

import com.example.Person.domain.model.Person;
import com.example.Person.infrastructure.adapters.mysqladapter.entity.PersonEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IPersonRepository extends ReactiveCrudRepository<PersonEntity, Long> {
    @Query("""
        SELECT per.* FROM persona per
        INNER JOIN bootcamp_persona bp ON per.id = bp.id_persona
        WHERE bp.id_bootcamp = :idBootcamp
    """)
    Flux<PersonEntity> getAllPeopleFromBootcamp(Long idBootcamp);
}

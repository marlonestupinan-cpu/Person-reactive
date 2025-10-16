package com.example.Person.infrastructure.adapters.mysqladapter.repository;

import com.example.Person.infrastructure.adapters.mysqladapter.entity.BootcampPersonEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IBootcampPersonRepository extends ReactiveCrudRepository<BootcampPersonEntity, Long> {
    Mono<Integer> countByIdBootcamp(Long id);

    @Query("""
        SELECT bp.id_bootcamp FROM bootcamp_persona bp
        GROUP BY bp.id_bootcamp
        ORDER BY COUNT(id_persona) DESC
        LIMIT 1
    """)
    Mono<Long> getBestBootcamp();
}

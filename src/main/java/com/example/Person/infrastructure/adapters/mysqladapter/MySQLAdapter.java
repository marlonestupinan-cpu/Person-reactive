package com.example.Person.infrastructure.adapters.mysqladapter;

import com.example.Person.domain.model.Bootcamp;
import com.example.Person.domain.model.Person;
import com.example.Person.domain.spi.IPersonPersistencePort;
import com.example.Person.infrastructure.adapters.mysqladapter.entity.BootcampPersonEntity;
import com.example.Person.infrastructure.adapters.mysqladapter.mapper.IPersonEntityMapper;
import com.example.Person.infrastructure.adapters.mysqladapter.repository.IBootcampPersonRepository;
import com.example.Person.infrastructure.adapters.mysqladapter.repository.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MySQLAdapter implements IPersonPersistencePort {
    private final IPersonRepository personRepository;
    private final IBootcampPersonRepository bootcampPersonRepository;
    private final IPersonEntityMapper personEntityMapper;

    @Override
    public Mono<Person> save(Person person) {
        return personRepository
                .save(personEntityMapper.toEntity(person))
                .map(personEntityMapper::toPerson);
    }

    @Override
    public Flux<BootcampPersonEntity> saveBootcampPerson(Person person, Flux<Bootcamp> bootcamps) {
        return bootcamps
                .flatMap(bootcamp -> bootcampPersonRepository.save(BootcampPersonEntity.builder()
                        .idBootcamp(bootcamp.getId())
                        .idPerson(person.getId())
                        .build())
                );
    }

    @Override
    public Mono<Integer> countPeopleFromBootcamp(Bootcamp bootcamp) {
        return bootcampPersonRepository.countByIdBootcamp(bootcamp.getId());
    }
}

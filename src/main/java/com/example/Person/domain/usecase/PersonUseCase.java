package com.example.Person.domain.usecase;

import com.example.Person.domain.api.IPersonServicePort;
import com.example.Person.domain.enums.TechnicalMessage;
import com.example.Person.domain.exceptions.BusinessException;
import com.example.Person.domain.model.Bootcamp;
import com.example.Person.domain.model.Person;
import com.example.Person.domain.spi.IBootcampGateway;
import com.example.Person.domain.spi.IPersonPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class PersonUseCase implements IPersonServicePort {
    private final IPersonPersistencePort personPersistencePort;
    private final IBootcampGateway bootcampGateway;

    @Override
    public Mono<Person> registerPerson(Person person) {
        if (person.getBootcamps().size() < Person.MIN_BOOTCAMP) {
            throw new BusinessException(TechnicalMessage.MIN_BOOTCAMP_NOT_REACHED, String.valueOf(Person.MIN_BOOTCAMP));
        }
        if (person.getBootcamps().size() > Person.MAX_BOOTCAMP) {
            throw new BusinessException(TechnicalMessage.MAX_BOOTCAMP_NOT_REACHED, String.valueOf(Person.MAX_BOOTCAMP));
        }
        if (new HashSet<>(person.getBootcamps()).size() < person.getBootcamps().size()) {
            throw new BusinessException(TechnicalMessage.DUPLICATED_BOOTCAMP);
        }

        return bootcampGateway
                .getBootcampsById(person
                        .getBootcamps()
                        .stream().map(Bootcamp::getId)
                        .toList()
                )
                .collectList()
                .flatMap(listBootcamp -> {
                    validateBootcampInterval(listBootcamp);
                    return personPersistencePort
                            .save(person)
                            .flatMap(personSaved -> personPersistencePort
                                    .saveBootcampPerson(personSaved, Flux.fromIterable(listBootcamp))
                                    .then(Mono.just(personSaved))
                            );
                });
    }

    private void validateBootcampInterval(List<Bootcamp> listBootcamp) {
        ArrayList<IntervalBootcamp> intervales = new ArrayList<>();
        listBootcamp
                .forEach(bootcamp -> {
                    Long a = bootcamp.getLaunchDate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                    Long b = a + bootcamp.getDurationInMilliseconds();

                    intervales.forEach(interval -> {
                        if (interval.b() < a || interval.a > b)
                            return;
                        throw new BusinessException(TechnicalMessage.BOOTCAMP_INTERSECTION, bootcamp.getName(), interval.name);
                    });
                    intervales.add(new IntervalBootcamp(a, b, bootcamp.getName()));
                });
    }

    record IntervalBootcamp(Long a, Long b, String name){}
}

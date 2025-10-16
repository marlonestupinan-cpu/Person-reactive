package com.example.Person.application;

import com.example.Person.domain.api.IPersonServicePort;
import com.example.Person.domain.spi.IBootcampGateway;
import com.example.Person.domain.spi.IPersonPersistencePort;
import com.example.Person.domain.spi.IReportGateway;
import com.example.Person.domain.usecase.PersonUseCase;
import com.example.Person.infrastructure.adapters.mysqladapter.MySQLAdapter;
import com.example.Person.infrastructure.adapters.mysqladapter.mapper.IPersonEntityMapper;
import com.example.Person.infrastructure.adapters.mysqladapter.repository.IBootcampPersonRepository;
import com.example.Person.infrastructure.adapters.mysqladapter.repository.IPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {
    @Bean
    public IPersonPersistencePort personPersistencePort(
            IPersonRepository personRepository,
            IBootcampPersonRepository bootcampPersonRepository,
            IPersonEntityMapper personEntityMapper
    ) {
        return new MySQLAdapter(personRepository, bootcampPersonRepository, personEntityMapper);
    }

    @Bean
    public IPersonServicePort personServicePort(
            IPersonPersistencePort personPersistencePort,
            IBootcampGateway bootcampGateway,
            IReportGateway reportGateway
    ) {
        return new PersonUseCase(personPersistencePort, bootcampGateway, reportGateway);
    }
}

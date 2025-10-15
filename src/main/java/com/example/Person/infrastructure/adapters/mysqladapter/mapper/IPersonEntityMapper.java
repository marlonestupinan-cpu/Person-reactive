package com.example.Person.infrastructure.adapters.mysqladapter.mapper;

import com.example.Person.domain.model.Person;
import com.example.Person.infrastructure.adapters.mysqladapter.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IPersonEntityMapper {
    Person toPerson(PersonEntity personEntity);
    PersonEntity toEntity(Person person);
}

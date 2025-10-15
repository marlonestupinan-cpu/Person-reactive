package com.example.Person.infrastructure.endpoints.mappers;

import com.example.Person.domain.model.Bootcamp;
import com.example.Person.domain.model.Person;
import com.example.Person.infrastructure.endpoints.dto.RegisterPerson;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IPersonDtoMapper {
    Person toPerson(RegisterPerson registerPerson);

    default Bootcamp capabilityIdToCapability(Long id) {
        return Bootcamp.builder().id(id).build();
    }
}

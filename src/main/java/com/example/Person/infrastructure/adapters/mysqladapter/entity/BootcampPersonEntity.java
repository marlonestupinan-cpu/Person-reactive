package com.example.Person.infrastructure.adapters.mysqladapter.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("bootcamp_persona")
@Data
@Builder
public class BootcampPersonEntity {
    @Id
    private Long id;
    @Column("id_persona")
    private Long idPerson;
    @Column("id_bootcamp")
    private Long idBootcamp;
}

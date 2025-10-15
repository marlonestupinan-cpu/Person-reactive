package com.example.Person.infrastructure.adapters.mysqladapter.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("persona")
public class PersonEntity {
    @Id
    private Long id;
    @Column("nombre")
    private String name;
    @Column("correo")
    private String email;
    @Column("edad")
    private Integer age;
}

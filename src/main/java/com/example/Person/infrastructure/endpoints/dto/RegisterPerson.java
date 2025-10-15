package com.example.Person.infrastructure.endpoints.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegisterPerson {
    private String name;
    private String email;
    private Integer age;

    private List<Long> bootcamps;
}

package com.example.Person.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Person {
    public static final int MIN_BOOTCAMP = 1;
    public static final int MAX_BOOTCAMP = 5;
    private Long id;
    private String name;
    private String email;
    private Integer age;

    private List<Bootcamp> bootcamps;
}

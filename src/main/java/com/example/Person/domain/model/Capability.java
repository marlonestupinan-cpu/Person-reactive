package com.example.Person.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class Capability {
    private Long id;
    private List<Technology> technologies;
}

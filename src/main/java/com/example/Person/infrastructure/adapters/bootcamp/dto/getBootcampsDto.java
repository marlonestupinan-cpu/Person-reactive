package com.example.Person.infrastructure.adapters.bootcamp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class getBootcampsDto {
    private List<Long> bootcamps;
}

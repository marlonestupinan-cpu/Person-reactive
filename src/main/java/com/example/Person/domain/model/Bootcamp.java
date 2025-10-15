package com.example.Person.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Bootcamp {
    private Long id;
    private String name;
    private LocalDateTime launchDate;
    private Long duration;

    public Long getDurationInMilliseconds() {
        return this.duration * 3_600_000;
    }
}

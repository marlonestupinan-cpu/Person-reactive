package com.example.Person.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Bootcamp {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime launchDate;
    private Long duration;

    private List<Capability> capabilities;

    public Long getDurationInMilliseconds() {
        return this.duration * 3_600_000;
    }
}

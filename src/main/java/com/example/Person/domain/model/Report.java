package com.example.Person.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;

@Data
@Builder
public class Report {
    private String name;
    private String description;
    private LocalDateTime launchDate;
    private Long duration;
    private Integer totalCapabilities;
    private Integer totalTechnologies;
    private Integer totalPeople;

    public static Report buildOf(Bootcamp bootcamp, Integer numPeople) {
        return Report.builder()
                .name(bootcamp.getName())
                .description(bootcamp.getDescription())
                .launchDate(bootcamp.getLaunchDate())
                .duration(bootcamp.getDuration())
                .totalCapabilities(bootcamp.getCapabilities().size())
                .totalTechnologies(bootcamp.getCapabilities().stream().reduce(new HashSet<Long>(), (set, capability) -> {
                    set.addAll(capability.getTechnologies().stream().map(Technology::getId).toList());
                    return set;
                }, (set1, set2) -> {
                    set1.addAll(set2);
                    return set1;
                }).size())
                .totalPeople(numPeople)
                .build();
    }
}

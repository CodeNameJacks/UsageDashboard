package com.saatchi.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class TeamData {
    @Id
    @GeneratedValue
    private Long id;
    private int teamId;
    private int totalCalls;
    private int tokensConsumed;
    private float estimatedCost;
    private String models;
    private LocalDate date;


}

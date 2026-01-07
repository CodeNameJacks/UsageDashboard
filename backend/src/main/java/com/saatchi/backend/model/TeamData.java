package com.saatchi.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class TeamData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_id")
    private int teamId;

    @Column(name = "total_calls")
    private int totalCalls;

    @Column(name = "tokens_consumed")
    private int tokensConsumed;

    @Column(name = "estimated_cost")
    private float estimatedCost;
    private String models;
    private LocalDate date;


}

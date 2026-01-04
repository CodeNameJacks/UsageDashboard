package com.saatchi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saatchi.backend.model.TeamData;

public interface TeamDataRepository extends JpaRepository<TeamData,Long> {

}

package com.saatchi.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saatchi.backend.model.TeamData;

public interface TeamDataRepository extends JpaRepository<TeamData,Long> {

    List<TeamData> findAllByTeamId(int team_id);

}

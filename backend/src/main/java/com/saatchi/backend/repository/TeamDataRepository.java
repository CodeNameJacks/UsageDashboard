package com.saatchi.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.saatchi.backend.model.TeamData;

/**
 * Repository interface for accessing TeamData entities from the database.
 * Provides methods to query usage data by teamId.
 */
@Repository
public interface TeamDataRepository extends JpaRepository<TeamData,Long> {

    /**
     * Retrieves all usage records for a specific team.
     * @param teamId - ID of the team
     * @return a list of TeamData objects for the given team
     */
    List<TeamData> findAllByTeamId(int team_id);

}

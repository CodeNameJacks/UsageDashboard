package com.saatchi.backend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
//import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.saatchi.backend.model.TeamData;
import com.saatchi.backend.projection.ModelUsageSummary;
import com.saatchi.backend.projection.TeamDataSummary;

/**
 * Repository interface for accessing TeamData entities from the database.
 * Provides methods to query usage data by teamId.
 */
@Repository
public interface TeamDataRepository extends JpaRepository<TeamData,Long> {

    //Totals
    /**
     * Retrieves all usage records for a specific team.
     * @param teamId - ID of the team
     * @return a list of TeamData objects for the given team
     */
    @Query("""
        SELECT
            td.date AS date,
            SUM(td.totalCalls) AS totalCalls,
            ROUND(SUM(td.estimatedCost), 2) AS totalEstimatedCost,
            SUM(td.tokensConsumed) AS totalTokens
        FROM TeamData td
        WHERE td.teamId = :teamId
        GROUP BY td.date
        ORDER BY td.date ASC
    """)
   List<TeamDataSummary> findTeamTotals(@Param("teamId") int teamId);


    // Top 3 models
    @Query("""
        SELECT
            td.models AS model,
            SUM(td.totalCalls) AS calls
        FROM TeamData td
        WHERE td.teamId = :teamId
        GROUP BY td.models
        ORDER BY SUM(td.totalCalls) DESC
    """)
    List<ModelUsageSummary> findTopModelsByTeamId(
        @Param("teamId") int teamId,
        Pageable pageable
    );
}

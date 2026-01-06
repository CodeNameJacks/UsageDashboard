package com.saatchi.backend.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
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

    /**
     * Retrieves daily aggregated usage data for a specific team.
     * Each entry contains the date, total API calls, total tokens consumed,
     * and total estimated cost for that day.
     * @param teamId the ID of the team
     * @return a list of TeamDataSummary projections containing daily totals
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


    /**
     * Retrieves the top models used by a team, sorted by total API calls in descending order.
     * @param teamId the ID of the team
     * @param pageable a Pageable object to limit results
     * @return a list of ModelUsageSummary projections containing model name and total calls
     */
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

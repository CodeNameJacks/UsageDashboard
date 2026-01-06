package com.saatchi.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.saatchi.backend.projection.ModelUsageSummary;
import com.saatchi.backend.projection.TeamDataSummary;
import com.saatchi.backend.repository.TeamDataRepository;

/**
 * Service layer responsible for fetching AI platform usage metrics for teams.
 * Separates business logic for retrieving usage data from the repository.
 */
@Service
public class TeamDataService {
    private final TeamDataRepository teamDataRepository;

    public TeamDataService(TeamDataRepository teamDataRepository){
        this.teamDataRepository = teamDataRepository;
    }

    /**
     * Retrieves usage data for a given team, including daily totals and top 3 models.
     * 
     * The returned map is structured as follows:
     * 
     * dailyTotals:
     *   date: list of dates
     *   totalCalls: list of total calls per date
     *   totalTokens: list of tokens consumed per date
     *   totalEstimatedCost: list of estimated costs per date
     * 
     * topModels:
     *   models: list of top 3 model names
     *   calls: list of API calls per top model
     *
     * @param teamId the ID of the team to retrieve data for
     * @return a map containing daily totals and top models for the team
     * @throws IllegalArgumentException if teamId is less than or equal to 0
     * @throws NoSuchElementException if no data exists for the given team
     */
    public Map<String, Object> getTeamDataByTeamId(int teamId) {

        if (teamId <= 0) {
            throw new IllegalArgumentException("A valid team id is required");
        }
        // get daily totals
        List<TeamDataSummary> totals = teamDataRepository.findTeamTotals(teamId);

        if (totals == null || totals.isEmpty()) {
            throw new NoSuchElementException("Team not found");
        }
        // get top models
        List<ModelUsageSummary> topModelsRaw =
            teamDataRepository.findTopModelsByTeamId(teamId, PageRequest.of(0, 3));

         // reshape top models
        List<String> models = new ArrayList<>();
        List<Long> calls = new ArrayList<>();

        for (ModelUsageSummary m : topModelsRaw) {
            models.add(m.getModel());
            calls.add(m.getCalls());
        }

        Map<String, Object> topModels = new HashMap<>();
        topModels.put("models", models);
        topModels.put("calls", calls);    

        //reshape daily totals
        List<LocalDate> dates = new ArrayList<>();
        List<Long> totalCallsArr = new ArrayList<>();
        List<Long> totalTokensArr = new ArrayList<>();
        List<Double> totalEstimatedCostsArr = new ArrayList<>();

        for (TeamDataSummary t : totals) {
            dates.add(t.getDate());
            totalCallsArr.add(t.getTotalCalls());
            totalTokensArr.add(t.getTotalTokens());
            totalEstimatedCostsArr.add(t.getTotalEstimatedCost());
        }

        Map<String, Object> dailyTotals = new HashMap<>();
        dailyTotals.put("date", dates);
        dailyTotals.put("totalCalls", totalCallsArr);
        dailyTotals.put("totalTokens", totalTokensArr);
        dailyTotals.put("totalEstimatedCost", totalEstimatedCostsArr);
        

        Map<String, Object> response = new HashMap<>();
        response.put("dailyTotals", dailyTotals);
        response.put("topModels", topModels);

        return response;
    }
}


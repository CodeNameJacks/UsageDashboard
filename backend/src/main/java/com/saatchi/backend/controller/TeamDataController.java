package com.saatchi.backend.controller;

import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.RestController;
import com.saatchi.backend.service.TeamDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



/**
 * REST controller for accessing AI platform usage metrics for teams.
 * Provides endpoints to fetch usage data by teamId.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5174")
public class TeamDataController {

    
    private final TeamDataService service;

    public TeamDataController(TeamDataService service){
        this.service = service;
    }

    /**
     * GET endpoint to retrieve team usage data.
     * Returns daily totals and top models for the specified team ID.
     * Example request: GET /team/3
     * @param teamId the ID of the team to retrieve data for
     * @return a map containing the team's daily totals and top models
     * @throws IllegalArgumentException if teamId is less than or equal to 0
     * @throws NoSuchElementException if no data exists for the team
     * @throws RuntimeException for unexpected errors
     */
    @GetMapping("/team/{teamId}")
    public Map<String, Object> getTeamData(@PathVariable int teamId) {
        
        try {
            return service.getTeamDataByTeamId(teamId);
        } catch (IllegalArgumentException | NoSuchElementException ex) {
            throw ex;

        } catch (Exception ex) {
            throw new RuntimeException("Failed to retrieve team data", ex);
        }
    }
}
    
    

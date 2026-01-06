package com.saatchi.backend.controller;

import java.util.Map;
import java.util.NoSuchElementException;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.saatchi.backend.model.TeamData;
import com.saatchi.backend.projection.TeamDataSummary;
import com.saatchi.backend.repository.TeamDataRepository;
import com.saatchi.backend.service.TeamDataService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


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
     * GET endpoint to retrieve all usage records for a specific team.
     *
     * @param teamId the ID of the team to fetch usage data for
     * @return a list of TeamData objects representing usage metrics
     * @throws IllegalArgumentException if teamId is less than or equal to 0
     * @throws java.util.NoSuchElementException if no records exist for the team
     */
    @GetMapping("/team/{teamId}")
    public Map<String, Object> getTeamData(@PathVariable int teamId) {
        
        try {
            return service.getTeamDataByTeamId(teamId);
        } catch (IllegalArgumentException | NoSuchElementException ex) {
            // rethrow so Spring returns 400/404 via default handling
            throw ex;

        } catch (Exception ex) {
            // wrap unexpected errors
            throw new RuntimeException("Failed to retrieve team data", ex);
        }
    }
}
    
    

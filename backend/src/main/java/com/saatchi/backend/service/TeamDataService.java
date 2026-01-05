package com.saatchi.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.saatchi.backend.model.TeamData;
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
     * Retrieves all usage data for a given team.
     *
     * @param teamId the ID of the team
     * @return a list of TeamData objects for the given team
     * @throws IllegalArgumentException if teamId is less than or equal to 0
     * @throws NoSuchElementException   if no records exist for the team
     */
    public List<TeamData> getTeamDataByTeamId(int teamId){
        if(teamId < 0 ){
            throw new IllegalArgumentException("A valid team id is required");
        }
       List<TeamData> data = teamDataRepository.findAllByTeamId(teamId);
       if(data.isEmpty()){
            throw new NoSuchElementException("Team not found");
       }

        return data;
    }
}

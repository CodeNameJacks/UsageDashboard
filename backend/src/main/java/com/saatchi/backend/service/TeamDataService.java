package com.saatchi.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.saatchi.backend.model.TeamData;
import com.saatchi.backend.repository.TeamDataRepository;

@Service
public class TeamDataService {
    private final TeamDataRepository teamDataRepository;

    public TeamDataService(TeamDataRepository teamDataRepository){
        this.teamDataRepository = teamDataRepository;
    }

    public List<TeamData> getTeamDataByTeamId(int teamId){
        if(teamId < 0 ){
            throw new IllegalArgumentException("A valid team id is required");
        }
       List<TeamData> data = teamDataRepository.findAllByTeamId(teamId);
       if(data.isEmpty()){
            throw new NoSuchElementException("Team not found");
       }

        System.out.println("DEBUG: Found team data: " + data);
        return data;
    }
}

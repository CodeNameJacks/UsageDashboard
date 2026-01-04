package com.saatchi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.saatchi.backend.model.TeamData;
import com.saatchi.backend.repository.TeamDataRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class TeamDataController {

    @Autowired
    private TeamDataRepository teamDataRepository;

    @PostMapping("/team")
    public TeamData save(@RequestBody TeamData data) {
            
        return teamDataRepository.save(data);
    }

    @GetMapping("/team")
    public List<TeamData> getAllTeamData() {
        return teamDataRepository.findAll();
    }
    
    

}

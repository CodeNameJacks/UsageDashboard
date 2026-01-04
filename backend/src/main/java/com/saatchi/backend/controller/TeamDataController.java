package com.saatchi.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.saatchi.backend.model.TeamData;
import com.saatchi.backend.repository.TeamDataRepository;
import com.saatchi.backend.service.TeamDataService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class TeamDataController {

    
    private final TeamDataService service;

    public TeamDataController(TeamDataService service){
        this.service = service;
    }

    /*@PostMapping("/team")
    public TeamData save(@RequestBody TeamData data) {
            
        return service.save(data);
    }
*/
    @GetMapping("/team/{teamId}")
    public List<TeamData> getTeamDataById(@PathVariable("teamId") int teamId) {
        List<TeamData> data = null;
        try {
            data = service.getTeamDataByTeamId(teamId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }
    
    

}

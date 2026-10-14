package com.liferpg.mission.weeklyMission.controller;

import com.liferpg.mission.weeklyMission.dto.CreateWeeklyMissions;
import com.liferpg.mission.weeklyMission.dto.WeeklyMissionsResponse;
import com.liferpg.mission.weeklyMission.service.WeeklyMissionsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weeklymission")
public class WeeklyMissionsController {

    private final WeeklyMissionsService weeklyMissionsService;

    public WeeklyMissionsController(WeeklyMissionsService weeklyMissionsService){
        this.weeklyMissionsService = weeklyMissionsService;
    }

    @PostMapping
    public ResponseEntity<WeeklyMissionsResponse> createWeeklyMission(@Valid @RequestBody CreateWeeklyMissions createWeeklyMissions){
       WeeklyMissionsResponse weeklyMission =  weeklyMissionsService.createWeeklyMission(createWeeklyMissions);
     return ResponseEntity.status(HttpStatus.CREATED).body(weeklyMission);
    }

    @GetMapping
    public ResponseEntity<List<WeeklyMissionsResponse>> findAllWeeklyMissions(){
      List<WeeklyMissionsResponse> findAll =  weeklyMissionsService.findAllWeeklyMissions();
    return ResponseEntity.ok(findAll);
    }

}

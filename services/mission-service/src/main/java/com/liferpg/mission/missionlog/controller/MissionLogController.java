package com.liferpg.mission.missionlog.controller;

import com.liferpg.mission.missionlog.dto.CreateMissionLogRequest;
import com.liferpg.mission.missionlog.dto.MissionLogResponse;
import com.liferpg.mission.weeklyMission.projection.CurrentWeeklyMissionProjection;
import com.liferpg.mission.missionlog.service.MissionLogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weekly-missions")
public class MissionLogController {

    private final MissionLogService missionLogService;

    public MissionLogController(MissionLogService missionLogService){
        this.missionLogService = missionLogService;
    }


    @PostMapping("{weeklyMissionId}/complete")
    public ResponseEntity<MissionLogResponse> completeMission(
            @PathVariable Long weeklyMissionId,
            @Valid @RequestBody CreateMissionLogRequest createMissionLogRequest){

       MissionLogResponse missionLog = missionLogService.completeMission(weeklyMissionId, createMissionLogRequest);

     return ResponseEntity.status(HttpStatus.CREATED).body(missionLog);
    }


}

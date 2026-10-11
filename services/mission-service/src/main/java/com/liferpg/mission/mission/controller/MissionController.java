package com.liferpg.mission.mission.controller;

import com.liferpg.mission.mission.dto.CreateMissionRequest;
import com.liferpg.mission.mission.dto.MissionResponse;
import com.liferpg.mission.mission.service.MissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService){
        this.missionService = missionService;
    }

    @PostMapping
    public ResponseEntity<MissionResponse> createMission(@Valid @RequestBody CreateMissionRequest request){

      MissionResponse missionResponse =  missionService.createMission(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(missionResponse);
    }

    @GetMapping
    public ResponseEntity<List<MissionResponse>> findMissionAll(){
      List<MissionResponse> missionResponseList=   missionService.findMissionAll();
      return ResponseEntity.ok(missionResponseList);
    }

}

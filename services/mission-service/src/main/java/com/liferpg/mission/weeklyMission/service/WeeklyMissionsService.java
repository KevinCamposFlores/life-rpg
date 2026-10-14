package com.liferpg.mission.weeklyMission.service;

import com.liferpg.mission.mission.entity.Mission;
import com.liferpg.mission.mission.exception.MissionInactiveException;
import com.liferpg.mission.mission.service.MissionService;
import com.liferpg.mission.weeklyMission.dto.CreateWeeklyMissions;
import com.liferpg.mission.weeklyMission.dto.WeeklyMissionsResponse;
import com.liferpg.mission.weeklyMission.entity.WeeklyMission;
import com.liferpg.mission.weeklyMission.entity.WeeklyMissionStatus;
import com.liferpg.mission.weeklyMission.exception.InvalidWeekRangeException;
import com.liferpg.mission.weeklyMission.mapper.WeeklyMissionsMapper;
import com.liferpg.mission.weeklyMission.repository.WeeklyMissionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeeklyMissionsService {

    private final WeeklyMissionsRepository weeklyMissionsRepository;
    private final MissionService missionService;

    public WeeklyMissionsService(WeeklyMissionsRepository weeklyMissionsRepository, MissionService missionService){
        this.weeklyMissionsRepository = weeklyMissionsRepository;
        this.missionService = missionService;
    }

    public WeeklyMissionsResponse createWeeklyMission(CreateWeeklyMissions createWeeklyMissions){
        if(createWeeklyMissions.weekStart().isAfter(createWeeklyMissions.weekEnd())){
            throw  new InvalidWeekRangeException("WeekStart can't be before weekEmd");
        }

        Mission mission = missionService.findByIdMission(createWeeklyMissions.missionId());

       if(!mission.getActive()){
           throw new MissionInactiveException("Mission inactive: "+ mission.getId());
       }

       WeeklyMission weeklyMission = WeeklyMissionsMapper.toEntity(createWeeklyMissions, mission);
       weeklyMission.setStatus(WeeklyMissionStatus.IN_PROGRESS);
        WeeklyMission weeklyMissionCreated = weeklyMissionsRepository.save(weeklyMission);
       return WeeklyMissionsMapper.toResponse(weeklyMissionCreated) ;
    }

    public List<WeeklyMissionsResponse> findAllWeeklyMissions(){
          return weeklyMissionsRepository.findWeeklyMissionsAll();
    }

}

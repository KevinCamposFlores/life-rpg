package com.liferpg.mission.mission.mapper;

import com.liferpg.mission.area.entity.Area;
import com.liferpg.mission.mission.dto.CreateMissionRequest;
import com.liferpg.mission.mission.dto.MissionResponse;
import com.liferpg.mission.mission.entity.Mission;
import com.liferpg.mission.mission.projection.MissionResponseProjection;

public class MissionMapper {

    public static Mission toEntity(CreateMissionRequest request, Area area){
        Mission mission = new Mission();
        mission.setArea(area);
        mission.setName(request.name());
        mission.setDescription(request.description());
        mission.setXpReward(request.xpReward());
        mission.setDifficult(request.difficult());
        mission.setMissionType(request.missionType());
        mission.setTargetValue(request.targetValue());
        mission.setUnit(request.unit());

   return mission;

    }

    public static MissionResponse toResponse(Mission mission){

        return new MissionResponse(mission.getId(),
                mission.getArea().getId(),
                mission.getArea().getName(),
                mission.getName(),
                mission.getDescription(),
                mission.getXpReward(),
                mission.getDifficult(),
                mission.getMissionType(),
                mission.getTargetValue(),
                mission.getUnit(),
                mission.getActive());
    }

    public static MissionResponse toResponse(MissionResponseProjection mission){
        return new MissionResponse(mission.id(),
                mission.areaId(),
                mission.areaName(),
                mission.name(),
                mission.description(),
                mission.xpReward(),
                mission.difficult(),
                mission.missionType(),
                mission.targetValue(),
                mission.unit(),
                mission.active());
    }

}

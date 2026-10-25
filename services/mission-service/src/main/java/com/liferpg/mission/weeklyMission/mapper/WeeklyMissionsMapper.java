package com.liferpg.mission.weeklyMission.mapper;

import com.liferpg.mission.mission.entity.Mission;
import com.liferpg.mission.mission.projection.MissionResponseProjection;
import com.liferpg.mission.missionlog.dto.CurrentWeeklyMissionResponse;
import com.liferpg.mission.weeklyMission.dto.CreateWeeklyMissions;
import com.liferpg.mission.weeklyMission.dto.WeeklyMissionsResponse;
import com.liferpg.mission.weeklyMission.entity.WeeklyMission;
import com.liferpg.mission.weeklyMission.projection.CurrentWeeklyMissionProjection;

import java.util.List;

public class WeeklyMissionsMapper {

    public static WeeklyMission toEntity(CreateWeeklyMissions request, Mission mission ){

        WeeklyMission weeklyMission = new WeeklyMission();
        weeklyMission.setMission(mission);
        weeklyMission.setWeekStart(request.weekStart());
        weeklyMission.setWeekEnd(request.weekEnd());
        weeklyMission.setTargetCount(request.targetCount());

        return weeklyMission;
    }

    public static WeeklyMissionsResponse toResponse(WeeklyMission weeklyMission){
        return new WeeklyMissionsResponse(
                weeklyMission.getId(),
                weeklyMission.getMission().getId(),
                weeklyMission.getMission().getName(),
                weeklyMission.getMission().getArea().getName(),
                weeklyMission.getWeekStart(),
                weeklyMission.getWeekEnd(),
                weeklyMission.getTargetCount(),
                weeklyMission.getStatus()
        );


    }
    public static CurrentWeeklyMissionResponse toCurrentResponse(CurrentWeeklyMissionProjection currentWeeklyMissionProjection ){
        return new CurrentWeeklyMissionResponse(
                currentWeeklyMissionProjection.getWeeklyMissionId(),
                currentWeeklyMissionProjection.getMissionId(),
                currentWeeklyMissionProjection.getMissionName(),
                currentWeeklyMissionProjection.getAreaId(),
                currentWeeklyMissionProjection.getAreaName(),
                currentWeeklyMissionProjection.getTargetValue(),
                currentWeeklyMissionProjection.getUnit(),
                currentWeeklyMissionProjection.getXpReward(),
                currentWeeklyMissionProjection.getCompletedCount(),
                currentWeeklyMissionProjection.getCompletedToday(),
                currentWeeklyMissionProjection.getTargetCount(),
                currentWeeklyMissionProjection.getStatus(),
                currentWeeklyMissionProjection.getWeekStart(),
                currentWeeklyMissionProjection.getWeekEnd()
        );
    }
}

package com.liferpg.mission.missionlog.mapper;

import com.liferpg.mission.missionlog.dto.CreateMissionLogRequest;
import com.liferpg.mission.missionlog.dto.CurrentWeeklyMissionResponse;
import com.liferpg.mission.missionlog.dto.MissionLogResponse;
import com.liferpg.mission.missionlog.entity.MissionLog;
import com.liferpg.mission.weeklyMission.entity.WeeklyMission;
import com.liferpg.mission.weeklyMission.entity.WeeklyMissionStatus;
import com.liferpg.mission.weeklyMission.projection.CurrentWeeklyMissionProjection;

import java.time.LocalDateTime;

public class MissionLogMapper {

    public static MissionLog toEntity (CreateMissionLogRequest createMissionLogRequest, WeeklyMission weeklyMission){

        MissionLog missionLog = new MissionLog();
        missionLog.setValue(createMissionLogRequest.value().doubleValue());
        missionLog.setNotes(createMissionLogRequest.notes());
        missionLog.setCompletedAt(LocalDateTime.now());
        missionLog.setWeeklyMission(weeklyMission);
        missionLog.setXpRewardSnapshot(weeklyMission.getMission().getXpReward());

        return missionLog;

    }

    public static MissionLogResponse toResponse (MissionLog missionLog, Long completedCount, Integer targetCount, WeeklyMissionStatus weeklyStatus){
        return new MissionLogResponse(
                missionLog.getId(),
                missionLog.getWeeklyMission().getId(),
                missionLog.getWeeklyMission().getMission().getId(),
                missionLog.getWeeklyMission().getMission().getName(),
                missionLog.getWeeklyMission().getMission().getArea().getId(),
                missionLog.getWeeklyMission().getMission().getArea().getName(),
                missionLog.getCompletedAt(),
                missionLog.getValue(),
                missionLog.getWeeklyMission().getMission().getUnit(),
                missionLog.getXpRewardSnapshot(),
                missionLog.getNotes(),
                completedCount.intValue(),
                targetCount,
                weeklyStatus);
    }


}

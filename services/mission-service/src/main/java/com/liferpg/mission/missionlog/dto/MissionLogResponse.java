package com.liferpg.mission.missionlog.dto;

import com.liferpg.mission.mission.entity.MissionUnit;
import com.liferpg.mission.weeklyMission.entity.WeeklyMissionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MissionLogResponse(

        Long id,
        Long weeklyMissionId,
        Long missionId,
        String missionName,
        Long areaId,
        String areaName,

        LocalDateTime completeAt,

        Double value,
        MissionUnit unit,

        Integer xpEarned,
        String notes,
        Integer completedCount,
        Integer targetCount,
        WeeklyMissionStatus weeklyStatus

) {
}

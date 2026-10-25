package com.liferpg.mission.missionlog.dto;

import com.liferpg.mission.mission.entity.MissionUnit;
import com.liferpg.mission.weeklyMission.entity.WeeklyMissionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CurrentWeeklyMissionResponse(
        Long weeklyMissionId,
        Long missionId,
        String missionName,
        Long areaId,
        String areaName,
        BigDecimal targetValue,
        MissionUnit unit,
        Integer xpReward,
        Long completedCount,
        Boolean completedToday,
        Integer targetCount,
        WeeklyMissionStatus status,
        LocalDate weekStart,
        LocalDate weekEnd
) {
}

package com.liferpg.mission.weeklyMission.dto;

import com.liferpg.mission.weeklyMission.entity.WeeklyMissionStatus;

import java.time.LocalDate;

public record WeeklyMissionsResponse(
        Long id,
        Long missionId,
        String missionName,
        String areaName,
        LocalDate weekStart,
        LocalDate weekEnd,
        Integer targetCount,
        WeeklyMissionStatus status
) {
}

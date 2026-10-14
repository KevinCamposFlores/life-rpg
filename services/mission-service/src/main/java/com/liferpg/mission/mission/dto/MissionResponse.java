package com.liferpg.mission.mission.dto;

import com.liferpg.mission.mission.entity.Difficult;
import com.liferpg.mission.mission.entity.MissionType;
import com.liferpg.mission.mission.entity.MissionUnit;

import java.math.BigDecimal;

public record MissionResponse(
        Long id,
        Long areaId,
        String areaName,
        String name,
        String description,
        Integer xpReward,
        Difficult difficult,
        MissionType missionType,
        BigDecimal targetValue,
        MissionUnit unit,
        Boolean active
) {
}

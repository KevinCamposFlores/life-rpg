package com.liferpg.mission.mission.dto;

import com.liferpg.mission.mission.entity.Difficult;
import com.liferpg.mission.mission.entity.MissionType;
import com.liferpg.mission.mission.entity.MissionUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateMissionRequest (

        @NotNull
        Long areaId,

        @NotBlank
        String name,

        String description,

        @NotNull
        @Positive
        Integer xpReward,
        @NotNull
        Difficult difficult,
        @NotNull
        MissionType missionType,
        @Positive
        BigDecimal targetValue,
        @NotNull
        MissionUnit unit ){
}

package com.liferpg.mission.missionlog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateMissionLogRequest (

        @NotNull
        @Positive
        BigDecimal value,

        @NotBlank
        String notes
) {
}

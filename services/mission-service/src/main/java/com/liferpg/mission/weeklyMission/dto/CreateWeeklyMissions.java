package com.liferpg.mission.weeklyMission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateWeeklyMissions  (

      @NotNull
      Long  missionId,
      @NotNull
      LocalDate weekStart,
      @NotNull
      LocalDate weekEnd,
      @NotNull
      Integer targetCount
){
}

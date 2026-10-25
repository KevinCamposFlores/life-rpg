package com.liferpg.mission.weeklyMission.projection;

import com.liferpg.mission.mission.entity.MissionUnit;
import com.liferpg.mission.weeklyMission.entity.WeeklyMissionStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface CurrentWeeklyMissionProjection {
Long getWeeklyMissionId();

Long getMissionId();

String getMissionName();

Long getAreaId();

String getAreaName();

BigDecimal getTargetValue();

MissionUnit getUnit();

Integer getXpReward();

Long getCompletedCount();

Boolean getCompletedToday();

Integer getTargetCount();

WeeklyMissionStatus getStatus();

LocalDate getWeekStart();

LocalDate getWeekEnd();

}

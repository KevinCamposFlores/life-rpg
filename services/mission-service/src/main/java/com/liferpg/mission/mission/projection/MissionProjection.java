package com.liferpg.mission.mission.projection;


import java.math.BigDecimal;

public interface MissionProjection {
Long getId();
Long getAreaId();
String getAreaName();
String getName();
String getDescription();
Integer getXpReward();
String getDifficult();
String getMissionType();
BigDecimal getTargetValue();
String getMissionUnit();
Boolean getActive();
}

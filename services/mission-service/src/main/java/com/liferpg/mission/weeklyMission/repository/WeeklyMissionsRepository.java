package com.liferpg.mission.weeklyMission.repository;

import com.liferpg.mission.mission.projection.MissionResponseProjection;
import com.liferpg.mission.weeklyMission.dto.WeeklyMissionsResponse;
import com.liferpg.mission.weeklyMission.entity.WeeklyMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeklyMissionsRepository extends JpaRepository<WeeklyMission, Long> {

    @Query("""
SELECT new com.liferpg.mission.weeklyMission.dto.WeeklyMissionsResponse(
  m.id,
  m.mission.id,
  m.mission.name,
  m.mission.area.name,
  m.weekStart,
  m.weekEnd,
  m.targetCount,
  m.status

) FROM WeeklyMission m
""")
    List<WeeklyMissionsResponse> findWeeklyMissionsAll();

}

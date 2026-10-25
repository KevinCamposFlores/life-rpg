package com.liferpg.mission.weeklyMission.repository;

import com.liferpg.mission.weeklyMission.projection.CurrentWeeklyMissionProjection;
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

    @Query(value = """
                SELECT
               w.id AS weeklyMissionId,
              m.id AS missionId,
           m.name AS missionName,
           a.id AS areaId,
              a.name AS areaName,
              m.target_value AS targetValue,
              m.unit AS unit,
              m.xp_reward AS xpReward,
              (SELECT COUNT(*)
                 FROM mission_log ml
                 WHERE ml.weekly_mission_id = w.id
            ) AS completedCount,
             EXISTS (SELECT
                  FROM mission_log ml
                  WHERE ml.weekly_mission_id = w.id
                     AND ml.completed_at >= CURRENT_DATE
                    AND ml.completed_at < CURRENT_DATE + INTERVAL '1 day'
              ) AS completedToday,
              w.target_count AS targetCount,
              w.status AS status,
                w.week_start AS weekStart,
               w.week_end AS weekEnd
            FROM weekly_mission w
            JOIN mission m
                ON w.mission_id = m.id
            JOIN area a
              ON m.area_id = a.id
            WHERE CURRENT_DATE BETWEEN w.week_start AND w.week_end AND m.active = true""" ,nativeQuery = true)
    List<CurrentWeeklyMissionProjection> findCurrentWeeklyMissions();


}

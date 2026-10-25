package com.liferpg.mission.missionlog.repository;

import com.liferpg.mission.missionlog.entity.MissionLog;
import com.liferpg.mission.weeklyMission.projection.CurrentWeeklyMissionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissionLogRepository extends JpaRepository<MissionLog, Long> {

    @Query(value = """
    SELECT COUNT(*) FROM mission_log ml 
    WHERE ml.weekly_middion_id = :id """, nativeQuery = true)
    Long findCountMissionLogForWeeklyMission(@Param("id") Long id);

    Long countByWeeklyMission_Id(Long weeklyMissionId);

    boolean existsByWeeklyMission_IdAndCompletedAtGreaterThanEqualAndCompletedAtLessThan(Long weeklyMissionId, LocalDateTime inicio, LocalDateTime fin);

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

package com.liferpg.mission.missionlog.repository;

import com.liferpg.mission.missionlog.entity.MissionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface MissionLogRepository extends JpaRepository<MissionLog, Long> {

    @Query(value = """
    SELECT COUNT(*) FROM mission_log ml 
    WHERE ml.weekly_middion_id = :id """, nativeQuery = true)
    Long findCountMissionLogForWeeklyMission(@Param("id") Long id);

    Long countByWeeklyMission_Id(Long weeklyMissionId);

    boolean existsByWeeklyMission_IdAndCompletedAtGreaterThanEqualAndCompletedAtLessThan(Long weeklyMissionId, LocalDateTime inicio, LocalDateTime fin);


}

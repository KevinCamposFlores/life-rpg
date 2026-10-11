package com.liferpg.mission.mission.repository;

import com.liferpg.mission.mission.entity.Mission;
import com.liferpg.mission.mission.projection.MissionResponseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

@Query("""
SELECT new com.liferpg.mission.mission.projection.MissionResponseProjection(
  m.id,
  m.areaId.id,
  m.areaId.name,
  m.name,
  m.description,
  m.xpReward,
  m.difficult,
  m.missionType,
  m.targetValue,
  m.unit,
  m.active

) FROM Mission m
WHERE m.active = true
""")
    List<MissionResponseProjection> findMissionAll();
}

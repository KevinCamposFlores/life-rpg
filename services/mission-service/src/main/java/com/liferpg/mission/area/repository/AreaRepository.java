package com.liferpg.mission.area.repository;

import com.liferpg.mission.area.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area,Long> {

    boolean existsByCode(String code);
    boolean existsByName(String name);
    boolean existsByCodeAndIdNot(String code, Long id);
    boolean existsByNameAndIdNot(String name, Long id);
}

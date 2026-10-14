package com.liferpg.mission.mission.service;

import com.liferpg.mission.area.entity.Area;
import com.liferpg.mission.area.exception.AreaIsDesactiveException;
import com.liferpg.mission.area.service.AreaService;
import com.liferpg.mission.mission.dto.CreateMissionRequest;
import com.liferpg.mission.mission.dto.MissionResponse;
import com.liferpg.mission.mission.entity.Mission;
import com.liferpg.mission.mission.exception.MissionNotFoundException;
import com.liferpg.mission.mission.mapper.MissionMapper;
import com.liferpg.mission.mission.projection.MissionResponseProjection;
import com.liferpg.mission.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final AreaService areaService;

    public MissionService(MissionRepository missionRepository, AreaService areaService){
        this.missionRepository = missionRepository;
        this.areaService = areaService;
    }

    @Transactional
    public MissionResponse createMission(CreateMissionRequest request){
       Area area = areaService.findById(request.areaId());

       if(!area.isActive()){
           throw  new AreaIsDesactiveException("Area is deactivated");
       }

       Mission mission  =  MissionMapper.toEntity(request, area);
       mission.setActive(true);
       Mission missionCreated = missionRepository.save(mission);

       return MissionMapper.toResponse(missionCreated);

    }

    @Transactional
    public List<MissionResponse> findMissionAll(){
      return  missionRepository.findMissionAll().stream().map(mission -> MissionMapper.toResponse(mission)).toList();
    }

    @Transactional
    public MissionResponseProjection findByIdMissionWithArea(Long id){
        return missionRepository.findByIdMissionWithArea(id).orElseThrow(()-> new MissionNotFoundException("Mission not found"));
    }

    public Mission findByIdMission(Long id){
       return missionRepository.findById(id).orElseThrow(() -> new MissionNotFoundException("Mission not found"));

    }







}

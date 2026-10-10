package com.liferpg.mission.area.service;

import com.liferpg.mission.area.dto.AreaResponse;
import com.liferpg.mission.area.dto.CreateAreaRequest;
import com.liferpg.mission.area.dto.UpdateAreaRequest;
import com.liferpg.mission.area.entity.Area;
import com.liferpg.mission.area.exception.AreaAlreadyStateDesactiveException;
import com.liferpg.mission.area.exception.AreaNotFoundException;
import com.liferpg.mission.area.exception.CodeAreaAlreadyExistsException;
import com.liferpg.mission.area.exception.NameAreaAlreadyExistsException;
import com.liferpg.mission.area.mapper.AreaMapper;
import com.liferpg.mission.area.repository.AreaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository){
        this.areaRepository = areaRepository;
    }

    public AreaResponse createArea(CreateAreaRequest request){

      boolean existsByCode =  areaRepository.existsByCode(request.code());
      boolean existsByName = areaRepository.existsByName(request.name());

      if(existsByCode){
        throw  new CodeAreaAlreadyExistsException("Code Area Already exist: " + request.code());
      }

      if(existsByName){
          throw  new NameAreaAlreadyExistsException("Name Area Already exist: "+ request.name());
      }

       Area area = AreaMapper.toEntity(request);
       area.setActive(true);

       Area createdarea =  areaRepository.save(area);

       return AreaMapper.toResponse(createdarea);

    }

    public Area findById(Long id){
        Area area =  areaRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("Area not found: "+id));
        return area;

    }

    public AreaResponse findAreaById(Long id){
        Area area =  areaRepository.findById(id).orElseThrow(() -> new AreaNotFoundException("Area not found: "+id));
        AreaResponse areaResponse = AreaMapper.toResponse(area);
     return areaResponse;

    }

    public List<AreaResponse> findAreaAll(){
       return  areaRepository.findAll().stream().map(area -> AreaMapper.toResponse(area)).toList();

    }

    @Transactional
    public AreaResponse updateArea(Long id, UpdateAreaRequest request){

      Area area = findById(id);

       boolean existsCode =  areaRepository.existsByCodeAndIdNot(request.code(), id);

       if(existsCode){
          throw new CodeAreaAlreadyExistsException("code already exists : " + request.code());
      }

       boolean existsName = areaRepository.existsByNameAndIdNot(request.name(), id);

       if(existsName){
           throw  new NameAreaAlreadyExistsException("name already exists: " + request.name());
       }

      AreaMapper.updateEntity(area,request);
      AreaResponse areaResponse =  AreaMapper.toResponse(area);
      return areaResponse;
    }

    @Transactional
    public void desactiveArea(Long id){
        Area area = findById(id);
        if(area.isActive()){
            throw  new AreaAlreadyStateDesactiveException("Area already descative: "+id);
        }

        area.setActive(false);

    }

}


package com.liferpg.mission.area.mapper;

import com.liferpg.mission.area.dto.AreaResponse;
import com.liferpg.mission.area.dto.CreateAreaRequest;
import com.liferpg.mission.area.dto.UpdateAreaRequest;
import com.liferpg.mission.area.entity.Area;

public class AreaMapper {

    public static Area toEntity(CreateAreaRequest request){
        Area area = new Area();
        area.setCode(request.code());
        area.setName(request.name());
        area.setDescription(request.description());
        return area;
    }

    public static AreaResponse toResponse(Area area){
        return new AreaResponse(area.getId(), area.getCode(), area.getName(), area.getDescription(), area.isActive());
    }

    public static void updateEntity(Area area, UpdateAreaRequest request){
        area.setCode(request.code());
        area.setName(request.name());
        area.setDescription(request.description());

    }

}

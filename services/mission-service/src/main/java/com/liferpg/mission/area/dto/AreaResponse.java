package com.liferpg.mission.area.dto;

public record AreaResponse(

        Long id,
        String code,
        String name,
        String description,
        boolean active

) {
}

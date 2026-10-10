package com.liferpg.mission.area.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateAreaRequest(
        @NotBlank
        String code,
        @NotBlank
        String name,
        String description) {
}

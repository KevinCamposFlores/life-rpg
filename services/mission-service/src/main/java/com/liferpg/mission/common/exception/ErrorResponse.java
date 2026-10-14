package com.liferpg.mission.common.exception;

public record ErrorResponse(

        int status,
        ErrorCode code,
        String message

) {
}

package com.liferpg.mission.common.exception;

import java.util.Map;

public record ValidationErrorResponse(
        int status,
        ErrorCode code,
        String message,
        Map<String,String> errors
) {
}

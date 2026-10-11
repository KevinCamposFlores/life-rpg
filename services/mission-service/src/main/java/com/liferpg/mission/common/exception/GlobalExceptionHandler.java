package com.liferpg.mission.common.exception;

import com.liferpg.mission.area.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CodeAreaAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlerCodeAreaAlreadyException( CodeAreaAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        ErrorCode.CODE_AREA_ALREADY_EXIST,
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(NameAreaAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlerNameAreaAlreadyException( NameAreaAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        ErrorCode.NAME_AREA_ALREADY_EXIST,
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(AreaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerAreaNotFoundException( AreaNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        ErrorCode.ARE_NOT_FOUND,
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(AreaAlreadyStateDesactiveException.class)
    public ResponseEntity<ErrorResponse> handlerAreaAlreadyStateDesactiveException( AreaAlreadyStateDesactiveException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        ErrorCode.AREA_ALREADY_STATE_DESACTIVE,
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            errors.put(field, message);
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ValidationErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        ErrorCode.VALIDATION_ERROR,
                        "Validation failed",
                        errors));

    }

    @ExceptionHandler(AreaIsDesactiveException.class)
    public ResponseEntity<ErrorResponse> handlerAreaIsDeactivatedException( AreaIsDesactiveException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        HttpStatus.CONFLICT.value(),
                        ErrorCode.AREA_IS_DEACTIVATED,
                        exception.getMessage()
                ));
    }

}

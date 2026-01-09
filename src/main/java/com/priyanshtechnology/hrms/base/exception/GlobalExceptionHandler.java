package com.priyanshtechnology.hrms.base.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.priyanshtechnology.hrms.base.response.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity.status(404)
                .body(new ApiResponse<>(404, ex.getMessage(), null));
    }
}

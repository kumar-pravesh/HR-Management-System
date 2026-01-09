package com.priyanshtechnology.hrms.base.exception;


import org.springframework.http.ResponseEntity;


import com.priyanshtechnology.hrms.base.response.ApiResponse;
import com.priyanshtechnology.hrms.base.response.ResponseStructure;

import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity.status(404)
                .body(new ApiResponse<>(404, ex.getMessage(), null));
    }
    
    
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleEmployeeNotFound(
            EmployeeNotFoundException ex) {

        ResponseStructure<String> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.NOT_FOUND.value());
        rs.setMessage(ex.getMessage());
        rs.setData(null);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rs);
    }
}

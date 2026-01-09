package com.priyanshtechnology.hrms.employee.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.priyanshtechnology.hrms.employee.dto.EmployeeProfileRequestDTO;
import com.priyanshtechnology.hrms.employee.service.EmployeeProfileService;

@RestController
@RequestMapping("/api/employee-profiles")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    public EmployeeProfileController(EmployeeProfileService employeeProfileService) {
        this.employeeProfileService = employeeProfileService;
    }

    @PostMapping
    public ResponseEntity<?> createProfile(
            @RequestBody EmployeeProfileRequestDTO dto) {

        return employeeProfileService.createEmployeeProfile(dto);
    }
}

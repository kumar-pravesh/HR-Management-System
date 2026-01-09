package com.priyanshtechnology.hrms.empselfservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.priyanshtechnology.hrms.base.response.ApiResponse;
import com.priyanshtechnology.hrms.empselfservice.dto.EmployeeProfileResponseDTO;
import com.priyanshtechnology.hrms.empselfservice.service.impl.ESSServiceImpl;

@RestController
@RequestMapping("/api/ess")
public class EssController {

    private final ESSServiceImpl essService;

    public EssController(ESSServiceImpl essService) {
        this.essService = essService;
    }

    @GetMapping("/profile/{empId}")
    public ResponseEntity<ApiResponse<EmployeeProfileResponseDTO>> getProfile(
            @PathVariable Long empId) {

        EmployeeProfileResponseDTO profile =
                essService.getEmployeeProfile(empId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee profile fetched successfully", profile)
        );
    }
}

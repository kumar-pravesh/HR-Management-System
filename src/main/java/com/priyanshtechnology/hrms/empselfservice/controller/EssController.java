package com.priyanshtechnology.hrms.empselfservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.priyanshtechnology.hrms.base.response.ApiResponse;
import com.priyanshtechnology.hrms.empselfservice.dto.*;
import com.priyanshtechnology.hrms.empselfservice.service.impl.ESSServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/ess")
public class EssController {

    private final ESSServiceImpl essService;

    public EssController(ESSServiceImpl essService) {
        this.essService = essService;
    }

    // ================= GET EMPLOYEE PROFILE =================
    @GetMapping("/{empId}")
    public ResponseEntity<ApiResponse<EmployeeProfileResponseDTO>> getProfile(
            @PathVariable Long empId) {

        EmployeeProfileResponseDTO profile = essService.getEmployeeProfile(empId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Employee profile fetched successfully", profile)
        );
    }

    // ================= APPLY LEAVE =================
    @PostMapping("/{empId}/leaves")
    public ResponseEntity<ApiResponse<LeaveResponseDTO>> applyLeave(
            @PathVariable Long empId,
            @RequestBody LeaveRequestDTO dto) {

        LeaveResponseDTO leave = essService.applyLeave(empId, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Leave applied successfully", leave)
        );
    }

    // ================= GET MY LEAVES =================
    @GetMapping("/{empId}/leaves")
    public ResponseEntity<ApiResponse<List<LeaveResponseDTO>>> getMyLeaves(
            @PathVariable Long empId) {

        List<LeaveResponseDTO> leaves = essService.getMyLeaves(empId);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Leaves fetched successfully", leaves)
        );
    }

    // ================= TEAM LEAD APPROVE/REJECT LEAVE =================
    @PutMapping("/leaves/{leaveId}/tl-approve")
    public ResponseEntity<ApiResponse<String>> teamLeadApprove(
            @PathVariable Long leaveId,
            @RequestParam boolean approve) {

        essService.teamLeadApprove(leaveId, approve);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Leave " + (approve ? "approved" : "rejected") + " by TL", null)
        );
    }

    // ================= MANAGER APPROVE/REJECT LEAVE =================
    @PutMapping("/leaves/{leaveId}/manager-approve")
    public ResponseEntity<ApiResponse<String>> managerApprove(
            @PathVariable Long leaveId,
            @RequestParam boolean approve) {

        essService.managerApprove(leaveId, approve);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Leave " + (approve ? "approved" : "rejected") + " by Manager", null)
        );
    }

    // ================= UPDATE PERSONAL DETAILS =================
    @PutMapping("/{empId}/update-personal")
    public ResponseEntity<ApiResponse<EmployeeProfileResponseDTO>> updatePersonalDetails(
            @PathVariable Long empId,
            @RequestBody EmployeePersonalDetailsUpdateDTO dto) {

        EmployeeProfileResponseDTO updatedProfile = essService.updatePersonalDetails(empId, dto);

        return ResponseEntity.ok(
                new ApiResponse<>(200, "Personal details updated successfully", updatedProfile)
        );
    }
}

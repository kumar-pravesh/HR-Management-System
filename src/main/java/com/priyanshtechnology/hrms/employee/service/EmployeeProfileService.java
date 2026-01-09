package com.priyanshtechnology.hrms.employee.service;

import org.springframework.http.ResponseEntity;

import com.priyanshtechnology.hrms.base.response.ResponseStructure;
import com.priyanshtechnology.hrms.employee.dto.EmployeeProfileRequestDTO;
import com.priyanshtechnology.hrms.employee.dto.EmployeeResponseDTO;

public interface EmployeeProfileService {
	ResponseEntity<ResponseStructure<EmployeeResponseDTO>> createEmployeeProfile(
            EmployeeProfileRequestDTO dto);

}

package com.priyanshtechnology.hrms.employee.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.priyanshtechnology.hrms.base.response.ResponseStructure;
import com.priyanshtechnology.hrms.employee.dto.EmployeeRequestDTO;
import com.priyanshtechnology.hrms.employee.dto.EmployeeResponseDTO;

public interface EmployeeService {
	 ResponseEntity<ResponseStructure<EmployeeResponseDTO>> createEmployee(EmployeeRequestDTO dto);

	    ResponseEntity<ResponseStructure<EmployeeResponseDTO>> getEmployeeById(Long empId);

	    ResponseEntity<ResponseStructure<List<EmployeeResponseDTO>>> getAllEmployees();

	    ResponseEntity<ResponseStructure<EmployeeResponseDTO>> updateEmployee(Long empId, EmployeeRequestDTO dto);

	    ResponseEntity<ResponseStructure<String>> deleteEmployee(Long empId);

}

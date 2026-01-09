package com.priyanshtechnology.hrms.employee.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.priyanshtechnology.hrms.base.exception.EmployeeNotFoundException;
import com.priyanshtechnology.hrms.base.response.ResponseStructure;
import com.priyanshtechnology.hrms.employee.dto.EmployeeRequestDTO;
import com.priyanshtechnology.hrms.employee.dto.EmployeeResponseDTO;
import com.priyanshtechnology.hrms.employee.entity.Employee;
import com.priyanshtechnology.hrms.employee.repository.EmployeeRepository;
import com.priyanshtechnology.hrms.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // ================= CREATE =================
    @Override
    public ResponseEntity<ResponseStructure<EmployeeResponseDTO>> createEmployee(EmployeeRequestDTO dto) {

        employeeRepository.findByEmail(dto.getEmail())
                .ifPresent(e -> {
                    throw new RuntimeException("Employee email already exists");
                });

        Employee employee = new Employee();
        employee.setEmail(dto.getEmail());

        Employee savedEmployee = employeeRepository.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(buildResponse("Employee created successfully", mapToResponse(savedEmployee)));
    }

    // ================= GET BY ID =================
    @Override
    public ResponseEntity<ResponseStructure<EmployeeResponseDTO>> getEmployeeById(Long empId) {

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return ResponseEntity.ok(
                buildResponse("Employee found", mapToResponse(employee)));
    }

    // ================= GET ALL =================
    @Override
    public ResponseEntity<ResponseStructure<List<EmployeeResponseDTO>>> getAllEmployees() {

        List<EmployeeResponseDTO> employees = employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ResponseStructure<List<EmployeeResponseDTO>> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("All employees fetched successfully");
        rs.setData(employees);

        return ResponseEntity.ok(rs);
    }

    // ================= UPDATE =================
    @Override
    public ResponseEntity<ResponseStructure<EmployeeResponseDTO>> updateEmployee(
            Long empId, EmployeeRequestDTO dto) {

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        employee.setEmail(dto.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok(
                buildResponse("Employee updated successfully", mapToResponse(updatedEmployee)));
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public ResponseEntity<ResponseStructure<String>> deleteEmployee(Long empId) {

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setActive(false);
        employeeRepository.save(employee);

        ResponseStructure<String> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Employee deactivated successfully");
        rs.setData(null);

        return ResponseEntity.ok(rs);
    }

    // ================= HELPER METHODS =================
    private EmployeeResponseDTO mapToResponse(Employee employee) {

        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setEmpId(employee.getEmpId());
        dto.setEmail(employee.getEmail());
        dto.setActive(employee.isActive());

        return dto;
    }

    private ResponseStructure<EmployeeResponseDTO> buildResponse(
            String message, EmployeeResponseDTO data) {

        ResponseStructure<EmployeeResponseDTO> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage(message);
        rs.setData(data);

        return rs;
    }
}

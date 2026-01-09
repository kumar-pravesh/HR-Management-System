package com.priyanshtechnology.hrms.employee.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Override
    public ResponseEntity<ResponseStructure<EmployeeResponseDTO>> createEmployee(EmployeeRequestDTO dto) {

        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        emp.setDepartment(dto.getDepartment());
        emp.setDesignation(dto.getDesignation());
        emp.setSalary(dto.getSalary());

        Employee saved = employeeRepository.save(emp);

        return ResponseEntity.ok(
                buildResponse(HttpStatus.OK, "Employee created", mapToResponse(saved))
        );

    }

    @Override
    public ResponseEntity<ResponseStructure<EmployeeResponseDTO>> getEmployeeById(Long empId) {

        Employee emp = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return ResponseEntity.ok(
                buildResponse(HttpStatus.OK, "Employee found", mapToResponse(emp))
        );

    }

    @Override
    public ResponseEntity<ResponseStructure<List<EmployeeResponseDTO>>> getAllEmployees() {

        List<EmployeeResponseDTO> list = employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();

        ResponseStructure<List<EmployeeResponseDTO>> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("All employees");
        rs.setData(list);

        return ResponseEntity.ok(rs);
    }

    @Override
    public ResponseEntity<ResponseStructure<EmployeeResponseDTO>> updateEmployee(Long empId, EmployeeRequestDTO dto) {

        Employee emp = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setName(dto.getName());
        emp.setDepartment(dto.getDepartment());
        emp.setDesignation(dto.getDesignation());
        emp.setSalary(dto.getSalary());

        Employee updated = employeeRepository.save(emp);

        return ResponseEntity.ok(
                buildResponse(HttpStatus.OK, "Employee found", mapToResponse(updated))
        );

    }

    @Override
    public ResponseEntity<ResponseStructure<String>> deleteEmployee(Long empId) {

        Employee emp = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setActive(false);
        employeeRepository.save(emp);

        ResponseStructure<String> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setMessage("Employee deactivated");
        rs.setData(null);

        return ResponseEntity.ok(rs);
    }

    // ===== Helper Methods =====

    private EmployeeResponseDTO mapToResponse(Employee emp) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setEmpId(emp.getEmpId());
        dto.setName(emp.getName());
        dto.setEmail(emp.getEmail());
        dto.setDepartment(emp.getDepartment());
        dto.setDesignation(emp.getDesignation());
        dto.setActive(emp.isActive());
        return dto;
    }

    private ResponseStructure<EmployeeResponseDTO> buildResponse(
            HttpStatus status, String msg, EmployeeResponseDTO data) {

        ResponseStructure<EmployeeResponseDTO> rs = new ResponseStructure<>();
        rs.setStatusCode(status.value());
        rs.setMessage(msg);
        rs.setData(data);
        return rs;
    }

}

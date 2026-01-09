package com.priyanshtechnology.hrms.employee.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.priyanshtechnology.hrms.base.response.ResponseStructure;
import com.priyanshtechnology.hrms.employee.dto.*;
import com.priyanshtechnology.hrms.employee.entity.*;
import com.priyanshtechnology.hrms.employee.repository.EmployeeRepository;
import com.priyanshtechnology.hrms.employee.service.EmployeeProfileService;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeRepository employeeRepository;

    public EmployeeProfileServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

   
    public ResponseEntity<ResponseStructure<EmployeeResponseDTO>> createEmployeeProfile(
            EmployeeProfileRequestDTO dto) {

        // ✅ Email uniqueness check
        employeeRepository.findByEmail(dto.getEmail())
                .ifPresent(e -> {
                    throw new RuntimeException("Employee email already exists");
                });

        // ========= EMPLOYEE =========
        Employee employee = new Employee();
        employee.setEmail(dto.getEmail());

        // ========= PERSONAL =========
        EmployeePersonalDetails personal = new EmployeePersonalDetails();
        personal.setFullName(dto.getPersonal().getFullName());
        personal.setDob(dto.getPersonal().getDob());
        personal.setGender(dto.getPersonal().getGender());
        personal.setBloodGroup(dto.getPersonal().getBloodGroup());

        employee.setPersonalDetails(personal);

        // ========= JOB =========
        EmployeeJobDetails job = new EmployeeJobDetails();
        job.setDepartment(dto.getJob().getDepartment());
        job.setDesignation(dto.getJob().getDesignation());
        job.setSalary(dto.getJob().getSalary());
        job.setJoiningDate(dto.getJob().getJoiningDate());

        if (dto.getJob().getReportingManagerId() != null) {
            Employee manager = employeeRepository.findById(
                    dto.getJob().getReportingManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
            job.setReportingManager(manager);
        }

        employee.setJobDetails(job);

        // ========= CONTACT =========
        EmployeeContactDetails contact = new EmployeeContactDetails();
        contact.setMobile(dto.getContact().getMobile());
        contact.setEmergencyContact(dto.getContact().getEmergencyContact());
        contact.setAddress(dto.getContact().getAddress());

        employee.setContactDetails(contact);

        // ========= SAVE =========
        Employee saved = employeeRepository.save(employee);

        // ========= RESPONSE =========
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setEmpId(saved.getEmpId());
        responseDTO.setEmail(saved.getEmail());
        responseDTO.setActive(saved.isActive());

        ResponseStructure<EmployeeResponseDTO> rs = new ResponseStructure<>();
        rs.setStatusCode(HttpStatus.CREATED.value());
        rs.setMessage("Employee profile created successfully");
        rs.setData(responseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(rs);
    }
}

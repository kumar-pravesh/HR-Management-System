package com.priyanshtechnology.hrms.empselfservice.service.impl;

import org.springframework.stereotype.Service;
 

import java.util.List;
import java.util.stream.Collectors;

import com.priyanshtechnology.hrms.base.enums.Status;
import com.priyanshtechnology.hrms.base.exception.ResourceNotFoundException;
import com.priyanshtechnology.hrms.employee.entity.*;
import com.priyanshtechnology.hrms.empselfservice.dto.*;
import com.priyanshtechnology.hrms.empselfservice.entity.LeaveRequest;
 
import com.priyanshtechnology.hrms.empselfservice.repository.EmployeeSerRepository;
import com.priyanshtechnology.hrms.empselfservice.repository.LeaveRequestRepository;

import jakarta.transaction.Transactional;

@Service
public class ESSServiceImpl {

    private final EmployeeSerRepository employeeRepository;
    private final LeaveRequestRepository leaveRepo;

    public ESSServiceImpl(EmployeeSerRepository employeeRepository, LeaveRequestRepository leaveRepo) {
        this.employeeRepository = employeeRepository;
        this.leaveRepo = leaveRepo;
    }
    
   
   

    public EmployeeProfileResponseDTO getEmployeeProfile(Long empId) {

        Employee employee = employeeRepository.findById(empId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + empId));

        EmployeeProfileResponseDTO dto = new EmployeeProfileResponseDTO();

        // Basic
        dto.setEmpId(employee.getEmpId());
        dto.setEmail(employee.getEmail());
        dto.setActive(employee.isActive());

        // Personal Details
        EmployeePersonalDetails personal = employee.getPersonalDetails();
        if (personal != null) {
            dto.setFullName(personal.getFullName());
            dto.setDob(personal.getDob());
            dto.setGender(personal.getGender());
            dto.setBloodGroup(personal.getBloodGroup());
        }

        // Job Details
        EmployeeJobDetails job = employee.getJobDetails();
        if (job != null) {
            dto.setDepartment(job.getDepartment());
            dto.setDesignation(job.getDesignation());
            dto.setJoiningDate(job.getJoiningDate());

            if (job.getReportingManager() != null &&
                job.getReportingManager().getPersonalDetails() != null) {
                dto.setReportingManagerName(
                        job.getReportingManager().getPersonalDetails().getFullName());
            }
        }

        // Contact Details
        EmployeeContactDetails contact = employee.getContactDetails();
        if (contact != null) {
            dto.setMobile(contact.getMobile());
            dto.setEmergencyContact(contact.getEmergencyContact());
            dto.setAddress(contact.getAddress());
        }

        return dto;
    }
  

// ================= EMPLOYEE =================

public LeaveResponseDTO applyLeave(Long empId, LeaveRequestDTO dto) {

Employee employee = employeeRepository.findById(empId)
  .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

LeaveRequest leave = new LeaveRequest();
leave.setStartDate(dto.getStartDate());
leave.setEndDate(dto.getEndDate());
leave.setReason(dto.getReason());
leave.setStatus(Status.SUBMITTED);
leave.setEmployee(employee);

leaveRepo.save(leave);

return mapToDTO(leave);
}

public List<LeaveResponseDTO> getMyLeaves(Long empId) {
return leaveRepo.findByEmployee_EmpId(empId)
  .stream()
  .map(this::mapToDTO)
  .collect(Collectors.toList());
}

// ================= TEAM LEAD =================

 
public void teamLeadApprove(Long leaveId, boolean approve) {

LeaveRequest leave = getLeave(leaveId);

leave.setStatus(
  approve ? Status.TL_APPROVED : Status.TL_REJECTED
);

leaveRepo.save(leave);
}

// ================= MANAGER =================


public void managerApprove(Long leaveId, boolean approve) {

LeaveRequest leave = getLeave(leaveId);

if (leave.getStatus() != Status.TL_APPROVED) {
throw new RuntimeException("Leave must be TL approved first");
}

leave.setStatus(
  approve ? Status.MANAGER_APPROVED : Status.MANAGER_REJECTED
);

leaveRepo.save(leave);
}

// ================= UTIL =================

private LeaveRequest getLeave(Long id) {
return leaveRepo.findById(id)
  .orElseThrow(() -> new ResourceNotFoundException("Leave not found"));
}

private LeaveResponseDTO mapToDTO(LeaveRequest leave) {
LeaveResponseDTO dto = new LeaveResponseDTO();
dto.setLeaveId(leave.getId());
dto.setEmployeeName(
  leave.getEmployee().getPersonalDetails().getFullName()
);
dto.setStartDate(leave.getStartDate());
dto.setEndDate(leave.getEndDate());
dto.setStatus(leave.getStatus());
return dto;
}
// update 

@Transactional
public EmployeeProfileResponseDTO updatePersonalDetails(Long empId,
                                                       EmployeePersonalDetailsUpdateDTO dto) {

    Employee employee = employeeRepository.findById(empId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + empId));

    EmployeePersonalDetails personal = employee.getPersonalDetails();
    if (personal == null) {
        personal = new EmployeePersonalDetails();
        personal.setEmployee(employee);
        employee.setPersonalDetails(personal);
    }

    personal.setFullName(dto.getFullName());
    personal.setDob(dto.getDob());
    personal.setGender(dto.getGender());
    personal.setBloodGroup(dto.getBloodGroup());

    // Save employee (cascades to personalDetails)
    employeeRepository.save(employee);

    // Return updated profile DTO
    return getEmployeeProfile(empId);
}
}

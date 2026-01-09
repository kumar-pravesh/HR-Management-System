package com.priyanshtechnology.hrms.employee.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.priyanshtechnology.hrms.employee.dto.JobDTO;
import com.priyanshtechnology.hrms.employee.entity.EmployeeJobDetails;

import com.priyanshtechnology.hrms.employee.service.EmployeeJobService;



@RestController
@RequestMapping("/api/job-details")
public class EmployeeJobController {

    @Autowired
    private EmployeeJobService service;
    

    @PostMapping("/addjob/{employeeId}")
    public EmployeeJobDetails addJobDetails(
            @PathVariable Long employeeId,
            @RequestBody JobDTO jobDTO) {
        return service.saveJobDetails(employeeId, jobDTO);
    }
    
  

    // GET, DELETE methods remain same...
}


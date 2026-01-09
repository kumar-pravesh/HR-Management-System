package com.priyanshtechnology.hrms.employee.service.impl;



import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.priyanshtechnology.hrms.employee.dto.JobDTO;
import com.priyanshtechnology.hrms.employee.entity.Employee;
import com.priyanshtechnology.hrms.employee.entity.EmployeeJobDetails;
import com.priyanshtechnology.hrms.employee.repository.EmployeeJobRepository;
import com.priyanshtechnology.hrms.employee.repository.EmployeeRepository;
import com.priyanshtechnology.hrms.employee.service.EmployeeJobService;

import java.util.List;

@Service
public class EmployeeJobServiceImpl implements EmployeeJobService {

    @Autowired
    private EmployeeJobRepository repository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    

    @Override
    public EmployeeJobDetails saveJobDetails(Long employeeId, JobDTO jobDTO) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Employee reportingManager = null;
        if (jobDTO.getReportingManagerId() != null) {
            reportingManager = employeeRepository.findById(jobDTO.getReportingManagerId())
                    .orElseThrow(() -> new RuntimeException("Manager not found"));
        }

        EmployeeJobDetails jobDetails = new EmployeeJobDetails();
        jobDetails.setEmployee(employee);
        jobDetails.setDepartment(jobDTO.getDepartment());
        jobDetails.setDesignation(jobDTO.getDesignation());
        jobDetails.setSalary(jobDTO.getSalary());
        jobDetails.setJoiningDate(jobDTO.getJoiningDate());
        jobDetails.setReportingManager(reportingManager);
        
        
         repository.save(jobDetails);
         employee.setJobDetails(jobDetails);
         employeeRepository.save(employee);
         return jobDetails;
    }


    @Override
    public EmployeeJobDetails getJobDetails(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeJobDetails> getAllJobDetails() {
        return repository.findAll();
    }

    @Override
    public void deleteJobDetails(Long id) {
        repository.deleteById(id);
    }
}


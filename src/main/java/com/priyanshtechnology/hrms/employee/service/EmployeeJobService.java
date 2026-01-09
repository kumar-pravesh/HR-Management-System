package com.priyanshtechnology.hrms.employee.service;



import com.priyanshtechnology.hrms.employee.dto.JobDTO;
import com.priyanshtechnology.hrms.employee.entity.EmployeeJobDetails;
import java.util.List;

public interface EmployeeJobService {
	 EmployeeJobDetails saveJobDetails(Long employeeId, JobDTO jobDTO);
	    EmployeeJobDetails getJobDetails(Long id);
	    List<EmployeeJobDetails> getAllJobDetails();
	    void deleteJobDetails(Long id);
}


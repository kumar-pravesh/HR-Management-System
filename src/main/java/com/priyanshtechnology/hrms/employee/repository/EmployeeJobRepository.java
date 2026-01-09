package com.priyanshtechnology.hrms.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.priyanshtechnology.hrms.employee.entity.EmployeeJobDetails;

public interface EmployeeJobRepository extends JpaRepository<EmployeeJobDetails, Long> {
	
}

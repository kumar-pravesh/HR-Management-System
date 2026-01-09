package com.priyanshtechnology.hrms.empselfservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.priyanshtechnology.hrms.employee.entity.Employee;

public interface EmployeeSerRepository extends JpaRepository<Employee, Long> {
	
}


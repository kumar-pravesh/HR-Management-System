package com.priyanshtechnology.hrms.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyanshtechnology.hrms.employee.entity.Employee;
import com.priyanshtechnology.hrms.employee.entity.EmployeeContactDetails;

public interface EmployeeContactRepository extends JpaRepository<EmployeeContactDetails, Long> {
	 Optional<EmployeeContactDetails> findByEmployee(Employee employee);
}

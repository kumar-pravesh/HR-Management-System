package com.priyanshtechnology.hrms.employee.dto;

import java.time.LocalDate;

public class JobDTO {
	private String department;
    private String designation;
    private double salary;
    private LocalDate joiningDate;
    private Long reportingManagerId;
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Long getReportingManagerId() {
		return reportingManagerId;
	}
	public void setReportingManagerId(Long reportingManagerId) {
		this.reportingManagerId = reportingManagerId;
	}
    
    
    

}

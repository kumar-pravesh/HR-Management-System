package com.priyanshtechnology.hrms.employee.dto;

public class EmployeeResponseDTO {
	 private Long empId;
	    private String name;
	    private String email;
	    private String department;
	    private String designation;
	    private boolean active;
		public Long getEmpId() {
			return empId;
		}
		public void setEmpId(Long empId) {
			this.empId = empId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
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
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
	    
	    

}

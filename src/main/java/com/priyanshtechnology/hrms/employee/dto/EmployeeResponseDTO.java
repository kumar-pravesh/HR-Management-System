package com.priyanshtechnology.hrms.employee.dto;

public class EmployeeResponseDTO {
	private Long empId;
	private String email;
	private boolean active;
	
	private JobDTO jobDetails;          
    private ContactDTO contactDetails;
    private PersonalDTO personalDetails;
	
	
	public JobDTO getJobDetails() {
		return jobDetails;
	}
	public void setJobDetails(JobDTO jobDetails) {
		this.jobDetails = jobDetails;
	}
	public ContactDTO getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(ContactDTO contactDetails) {
		this.contactDetails = contactDetails;
	}
	public PersonalDTO getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(PersonalDTO personalDetails) {
		this.personalDetails = personalDetails;
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}

package com.priyanshtechnology.hrms.employee.dto;

public class EmployeeProfileRequestDTO {
	private String email;

    private PersonalDTO personal;
    private JobDTO job;
    private ContactDTO contact;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public PersonalDTO getPersonal() {
		return personal;
	}
	public void setPersonal(PersonalDTO personal) {
		this.personal = personal;
	}
	public JobDTO getJob() {
		return job;
	}
	public void setJob(JobDTO job) {
		this.job = job;
	}
	public ContactDTO getContact() {
		return contact;
	}
	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}

    

}

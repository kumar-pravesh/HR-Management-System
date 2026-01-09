package com.priyanshtechnology.hrms.employee.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column(nullable = false, unique = true)
    private String email;

    private boolean active = true;

    // ========= MAPPINGS =========

    @JsonManagedReference
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeePersonalDetails personalDetails;

    @JsonManagedReference
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeJobDetails jobDetails;

    @JsonManagedReference
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private EmployeeContactDetails contactDetails;

    // ========= GETTERS / SETTERS =========

    public Long getEmpId() {
        return empId;
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

    public EmployeePersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(EmployeePersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
        if (personalDetails != null)
            personalDetails.setEmployee(this);
    }

    public EmployeeJobDetails getJobDetails() {
        return jobDetails;
    }

    public void setJobDetails(EmployeeJobDetails jobDetails) {
        this.jobDetails = jobDetails;
        if (jobDetails != null)
            jobDetails.setEmployee(this);
    }

    public EmployeeContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(EmployeeContactDetails contactDetails) {
        this.contactDetails = contactDetails;
        if (contactDetails != null)
            contactDetails.setEmployee(this);
    }
}

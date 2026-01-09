package com.priyanshtechnology.hrms.employee.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_contact_details")
public class EmployeeContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobile;
    private String emergencyContact;
    private String address;

    @OneToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    // GETTERS / SETTERS

    public Long getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

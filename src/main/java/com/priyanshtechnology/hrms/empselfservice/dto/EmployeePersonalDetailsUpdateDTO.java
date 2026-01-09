package com.priyanshtechnology.hrms.empselfservice.dto;

import java.time.LocalDate;

 

public class EmployeePersonalDetailsUpdateDTO {

 
    private String fullName;

   
    private LocalDate dob;

 
    private String gender;

    private String bloodGroup;

    // ================= GETTERS & SETTERS =================

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}

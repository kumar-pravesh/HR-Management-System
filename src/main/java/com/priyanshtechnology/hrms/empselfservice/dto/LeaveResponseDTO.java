package com.priyanshtechnology.hrms.empselfservice.dto;
 

import java.time.LocalDate;

import com.priyanshtechnology.hrms.base.enums.Status;

public class LeaveResponseDTO {

    private Long leaveId;
    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Status status;

    // ================= GETTERS & SETTERS =================

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

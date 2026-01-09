package com.priyanshtechnology.hrms.employee.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "employee_job_details")
public class EmployeeJobDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String department;
    private String designation;
    private double salary;
    private LocalDate joiningDate;

    // Reporting Manager (Self reference)
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee reportingManager;

    @OneToOne
    @JoinColumn(name = "emp_id", nullable = false)
    private Employee employee;

    // GETTERS / SETTERS

    public Long getId() {
        return id;
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

    public Employee getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(Employee reportingManager) {
        this.reportingManager = reportingManager;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

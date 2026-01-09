 package com.priyanshtechnology.hrms.empselfservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyanshtechnology.hrms.empselfservice.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee_EmpId(Long empId);
}

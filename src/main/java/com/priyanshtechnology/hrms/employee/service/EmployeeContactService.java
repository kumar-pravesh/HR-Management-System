package com.priyanshtechnology.hrms.employee.service;

import com.priyanshtechnology.hrms.employee.dto.ContactDTO;
import com.priyanshtechnology.hrms.employee.entity.EmployeeContactDetails;
import java.util.List;

public interface EmployeeContactService {
	EmployeeContactDetails saveContactDetails(Long employeeId, ContactDTO dto);
    EmployeeContactDetails getContactDetails(Long id);
    List<EmployeeContactDetails> getAllContactDetails();
    void deleteContactDetails(Long id);
}

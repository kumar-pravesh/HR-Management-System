package com.priyanshtechnology.hrms.employee.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.priyanshtechnology.hrms.employee.entity.Employee;
import com.priyanshtechnology.hrms.employee.entity.EmployeeContactDetails;
import com.priyanshtechnology.hrms.employee.repository.EmployeeContactRepository;
import com.priyanshtechnology.hrms.employee.repository.EmployeeRepository;
import com.priyanshtechnology.hrms.employee.service.EmployeeContactService;
import com.priyanshtechnology.hrms.employee.dto.ContactDTO;

import java.util.List;

@Service
public class EmployeeContactServiceImpl implements EmployeeContactService {

    @Autowired
    private EmployeeContactRepository contactRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeContactDetails saveContactDetails(Long employeeId, ContactDTO dto) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        
     

        EmployeeContactDetails contact = new EmployeeContactDetails();
        contact.setEmployee(employee);
        contact.setMobile(dto.getMobile());
        contact.setEmergencyContact(dto.getEmergencyContact());
        contact.setAddress(dto.getAddress());
        employee.setContactDetails(contact);

         contactRepository.save(contact);
         employeeRepository.save(employee);
         return contact;
    }

    @Override
    public EmployeeContactDetails getContactDetails(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public List<EmployeeContactDetails> getAllContactDetails() {
        return contactRepository.findAll();
    }

    @Override
    public void deleteContactDetails(Long id) {
        contactRepository.deleteById(id);
    }
}

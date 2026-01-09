package com.priyanshtechnology.hrms.employee.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.priyanshtechnology.hrms.employee.entity.EmployeeContactDetails;
import com.priyanshtechnology.hrms.employee.dto.ContactDTO;
import com.priyanshtechnology.hrms.employee.service.EmployeeContactService;

import java.util.List;

@RestController
@RequestMapping("/api/contact-details")
public class EmployeeContactController {

    @Autowired
    private EmployeeContactService service;

    
    @PostMapping("/add/{employeeId}")
    public EmployeeContactDetails addContactDetails(
            @PathVariable Long employeeId,
            @RequestBody ContactDTO dto) {
        return service.saveContactDetails(employeeId, dto);
    }

    // GET single
    @GetMapping("/{id}")
    public EmployeeContactDetails getContactDetails(@PathVariable Long id) {
        return service.getContactDetails(id);
    }

    // GET all
    @GetMapping
    public List<EmployeeContactDetails> getAllContactDetails() {
        return service.getAllContactDetails();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteContactDetails(@PathVariable Long id) {
        service.deleteContactDetails(id);
    }
}

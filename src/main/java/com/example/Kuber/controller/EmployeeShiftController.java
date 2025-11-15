package com.example.Kuber.controller;

import com.example.Kuber.model.EmployeeShift;
import com.example.Kuber.repository.EmployeeshiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employeeshift")
public class EmployeeShiftController{

    @Autowired
    private EmployeeshiftRepository employeeshiftRepository;

    @PostMapping("/addshift")
    public String addNewEmployeeShift(@RequestBody EmployeeShift employeeShift){
        // Calculate duration before saving

    employeeshiftRepository.save(employeeShift);
    return "Shift Added Successfully";
    }

}

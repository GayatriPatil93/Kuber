package com.example.Kuber.controller;

import com.example.Kuber.model.LeaveTypes;
import com.example.Kuber.service.LeaveTypesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leave")
public class LeaveManagementController {

    private final LeaveTypesService leaveTypesService;
    public LeaveManagementController(LeaveTypesService leaveTypesService){
        this.leaveTypesService = leaveTypesService;

    }

    //add leavetype
    @PostMapping("/leaveType/add")
    public ResponseEntity<?> addLeaveType(@RequestBody LeaveTypes leaveTypes){
        return leaveTypesService.addleaveType(leaveTypes);
    }

    //edit leaveType
    @PutMapping("/leaveType/edit/{id}")
    public Optional<ResponseEntity<String>> edit(@PathVariable Long id, @RequestBody LeaveTypes updatedLeaveTypes){
        return leaveTypesService.editleaveType(id,updatedLeaveTypes);
    }

    //Delete LeaveType
    @DeleteMapping("/delete/{id}")
    public Optional<ResponseEntity<String>> delete(@PathVariable Long id){
        return leaveTypesService.deleteleaveType(id);
    }

    //get
    @GetMapping("/get")
    public List<LeaveTypes> get(){
        return leaveTypesService.getall();
    }




}

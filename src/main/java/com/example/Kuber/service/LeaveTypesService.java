package com.example.Kuber.service;

import com.example.Kuber.model.LeaveTypes;
import com.example.Kuber.repository.LeaveTypesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveTypesService {

    private final LeaveTypesRepository leaveTypesRepository;

    public LeaveTypesService( LeaveTypesRepository leaveTypesRepository){
        this.leaveTypesRepository = leaveTypesRepository;

    }

    public ResponseEntity<String> addleaveType(LeaveTypes leaveTypes){
        leaveTypesRepository.save(leaveTypes);
        return ResponseEntity.ok("Leave Type added successfully.");
    }

    public Optional<ResponseEntity<String>> editleaveType(Long id, LeaveTypes updatedleaveManagement) {
        return leaveTypesRepository.findById(id)
                .map(LeaveManagement -> {
                    LeaveManagement.setLeaveType(updatedleaveManagement.getLeaveType());
                    LeaveManagement.setLeavename(updatedleaveManagement.getLeavename());
                    LeaveManagement.setLeaveUnit(updatedleaveManagement.getLeaveUnit());
                    LeaveManagement.setMaxLeaves(updatedleaveManagement.getMaxLeaves());
                    LeaveManagement.setCreatedBy(updatedleaveManagement.getCreatedBy());
                    LeaveManagement.setStatus(updatedleaveManagement.getStatus());
                    LeaveManagement.setNote(updatedleaveManagement.getNote());
                    LeaveManagement.setDuration(updatedleaveManagement.getDuration());
                    LeaveManagement.setAnualLimit(updatedleaveManagement.getAnualLimit());
                    LeaveManagement.setCarryOver(updatedleaveManagement.getCarryOver());

                    leaveTypesRepository.save(updatedleaveManagement);
                    return ResponseEntity.ok("LeaveType edited successfully.");
                });
    }
        //delete leavetype
        public Optional<ResponseEntity<String>> deleteleaveType(Long id) {
        return leaveTypesRepository.findById(id)
                .map(leaveManagement -> {
                    leaveTypesRepository.delete(leaveManagement);
                    return ResponseEntity.ok("LeaveType deleted Successfully.");
                });

    }

    public List<LeaveTypes> getall(){
        return leaveTypesRepository.findAll();
    }



}
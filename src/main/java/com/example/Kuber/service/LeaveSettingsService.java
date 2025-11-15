package com.example.Kuber.service;

import com.example.Kuber.model.LeaveSettings;
import com.example.Kuber.repository.LeaveSettingsRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveSettingsService {

    private final LeaveSettingsRepo leaveSettingsRepo;

    public LeaveSettingsService(LeaveSettingsRepo leaveSettingsRepo){
        this.leaveSettingsRepo = leaveSettingsRepo;
    }

    public ResponseEntity<String> save(LeaveSettings leaveSettings){
         leaveSettingsRepo.save(leaveSettings);
        return ResponseEntity.ok("Leave Setting Saved..");
    }

    public List<LeaveSettings> get(){
        return leaveSettingsRepo.findAll();
    }
}

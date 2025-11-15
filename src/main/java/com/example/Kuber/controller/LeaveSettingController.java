package com.example.Kuber.controller;

import com.example.Kuber.model.LeaveSettings;
import com.example.Kuber.service.LeaveSettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leavesetting")
public class LeaveSettingController {

    private final LeaveSettingsService leaveSettingsService;

    public LeaveSettingController(LeaveSettingsService leaveSettingsService){
        this.leaveSettingsService = leaveSettingsService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody LeaveSettings leaveSettings){
        return leaveSettingsService.save(leaveSettings);
    }

    @GetMapping("/get")
    public List<LeaveSettings> get(){
        return leaveSettingsService.get();
    }

}

package com.example.Kuber.controller;

import com.example.Kuber.dto.LeaveBalanceDTO;
import com.example.Kuber.model.LeaveBalance;
import com.example.Kuber.service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leaveBalance")
public class LeaveBalanceController {

    @Autowired
    private LeaveBalanceService leaveBalanceService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody LeaveBalance leaveBalance){
        return leaveBalanceService.addLeaveBalance(leaveBalance);
    }
    @PutMapping("/edit/{id}")
    public Optional<ResponseEntity<String>> edit(@PathVariable Long id, @RequestBody LeaveBalance UpdatedLeaveBalance){
        return leaveBalanceService.updateLeaveBalance(id,UpdatedLeaveBalance);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return leaveBalanceService.deleteBalance(id);
    }
    @GetMapping("/getall")
    public List<LeaveBalanceDTO> getall(@RequestParam (required = false) Integer page,
                                        @RequestParam (required = false) Integer size){
        return leaveBalanceService.getall(page,size);
    }

    @GetMapping("/search")
    public Page<LeaveBalanceDTO> search(@RequestParam String value,
                                        @RequestParam Integer page,
                                        @RequestParam Integer size){
        return leaveBalanceService.search(value, page, size);
    }

}

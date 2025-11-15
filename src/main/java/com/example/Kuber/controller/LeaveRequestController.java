package com.example.Kuber.controller;

import com.example.Kuber.dto.LeaveReqStatusDTO;
import com.example.Kuber.dto.LeaveRequestDto;
import com.example.Kuber.model.LeaveRequest;
import com.example.Kuber.service.LeaveRequestService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/leaveRequest")
public class LeaveRequestController {

private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    //add request
    @PostMapping("/add")
    public ResponseEntity<String> add( @RequestBody LeaveRequest leaveRequest){
        return leaveRequestService.addRequest(leaveRequest);
    }
    //Fetch Requests
    @GetMapping("/getall")
    public ResponseEntity<List<LeaveRequestDto>> get(@RequestParam (required = false) Integer page,
                                     @RequestParam (required = false) Integer size){

        List<LeaveRequestDto> dtolist = leaveRequestService.getall(page, size);
        return ResponseEntity.ok(dtolist);
    }
    // fetch by any value (search specification)
    @GetMapping("/search")
    public Page<LeaveRequest> search(@RequestParam String value,
                                     @RequestParam (required = false) Integer page,
                                     @RequestParam (required = false) Integer size){
        return leaveRequestService.search(value, page, size);
    }
    
    //edit request
    @PutMapping("/edit/{id}")
    public Optional<ResponseEntity<String>> edit(@PathVariable Long id, LeaveRequest updatedLeaveRequest){
        return leaveRequestService.editRequest(id, updatedLeaveRequest);
        
    }
    //update status when leaveRequest approved or rejected
    @Transactional
    @PutMapping("/approveOrReject/{id}")
    public ResponseEntity<String> approveOrRejectLeave( @RequestBody LeaveReqStatusDTO dto) {
        return leaveRequestService.approveOrRejectLeave(dto);
    }
    //delete request\
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return leaveRequestService.delete(id);
    }



}



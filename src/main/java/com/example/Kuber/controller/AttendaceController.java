package com.example.Kuber.controller;

import com.example.Kuber.dto.MonthlyAttendanceDto;
import com.example.Kuber.model.Attendance;
import com.example.Kuber.repository.AttendanceRepository;
import com.example.Kuber.repository.EmployeesRepository;
import com.example.Kuber.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendaceController {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private AttendanceService attendanceService;

    //add new entry
    @PostMapping("/add")
    public ResponseEntity<?> addEmpAttendance(@RequestBody Attendance attendance){
        return attendanceService.addEmpAttendance(attendance);
    }

    //get all entries with pagination
    @GetMapping("/getattendance")
    public ResponseEntity<?> getTodaysAttendance(
            @RequestParam (required = false) Integer page,
            @RequestParam (required = false) Integer size) {
        return attendanceService.getTodaysAttendance(page, size);
    }

    //get attendance by id
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }
    //get attendance using dto
    @GetMapping("/details")
    public MonthlyAttendanceDto getAttendanceDetails(
            @RequestParam int year,
            @RequestParam int month){

        return attendanceService.getMonthlyAttendance(year,month);
    }

    //Update attendance
    @PutMapping("/update/{id}")
    public ResponseEntity<?> edittAttendace(@PathVariable Long id, @RequestBody Attendance updatedattendace){
        return attendanceService.updateAttendance(id,updatedattendace);
    }

    //delete attendance
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteattendance(@PathVariable Long id){
        return attendanceService.deleteAttendance(id);
    }


    //get attendance by year and month
    @GetMapping("/get/{year}/{month}")
    public List<?> getattendanceByYear(@PathVariable Integer year, @PathVariable Integer month){
        return attendanceService.getByYearAndMonth(year,month);
    }
    //search
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String value,
                                    @RequestParam int page,
                                    @RequestParam int size) {
        Page<Attendance> result = attendanceService.search(value, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("content", result.getContent());
        response.put("currentPage", result.getNumber());
        response.put("totalItems", result.getTotalElements());
        response.put("totalPages", result.getTotalPages());
        response.put("pageSize", result.getSize());

        return ResponseEntity.ok(response);
    }


}
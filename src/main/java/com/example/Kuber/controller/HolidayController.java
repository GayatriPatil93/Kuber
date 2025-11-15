package com.example.Kuber.controller;

import com.example.Kuber.model.Holiday;
import com.example.Kuber.service.HolidayService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/holiday")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    //add holiday
    @PostMapping("/add")
    public ResponseEntity<String> addHoliday(@RequestBody Holiday holiday){
        holidayService.addHoliday(holiday);
        return  ResponseEntity.ok("Holiday Added Successfully");
    }

    //All Holidays
    @GetMapping("/getall")
    public ResponseEntity<?> getAllHolidays(){
        return ResponseEntity.ok(holidayService.getAll());
    }

    //update holiday
    @PutMapping("/update/{id}")
    public Optional<ResponseEntity<String>> updateHoliday(@PathVariable Long id, @RequestBody Holiday updatedHoliday) {
        return holidayService.editHoliday(id,updatedHoliday);

    }

    //delete holiday
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHoliday(@PathVariable Long id) {
        return holidayService.deleteHoliday(id);
    }

    //search specification
    @GetMapping("/search")
    public Page<Holiday> search(
            @RequestParam String value,
            @RequestParam Integer page,
            @RequestParam Integer size
            ){
        return holidayService.search(value, page, size);
    }
}

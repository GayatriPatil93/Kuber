package com.example.Kuber.service;
import com.example.Kuber.model.Holiday;
import com.example.Kuber.repository.HolidayRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;

    //Constructor Injection
    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public ResponseEntity<String> addHoliday(Holiday holiday) {
        holidayRepository.save(holiday);
        return ResponseEntity.ok("Holiday added successfully.");
    }

    public List<Holiday> getAll() {
        return holidayRepository.findAll();
    }

    public Optional<ResponseEntity<String>> editHoliday(Long id, Holiday updatedHoliday) {
        return holidayRepository.findById(id).
                map(holiday ->
                {
                    holiday.setName(updatedHoliday.getName());
                    holiday.setDate(updatedHoliday.getDate());
                    holiday.setShift(updatedHoliday.getShift());
                    holiday.setLocation(updatedHoliday.getLocation());
                    holiday.setHolidayType(updatedHoliday.getHolidayType());
                    holiday.setCreatedBy(updatedHoliday.getCreatedBy());
                    holiday.setDetails(updatedHoliday.getDetails());
                    holiday.setApprovalStatus(updatedHoliday.getApprovalStatus());
                    holidayRepository.save(updatedHoliday);
                    return ResponseEntity.ok("Holiday Updated Successfully.");
                });
    }

    public ResponseEntity<String> deleteHoliday(Long id) {
        return holidayRepository.findById(id)
                .map(holiday -> {
                    holidayRepository.delete(holiday);
                    return ResponseEntity.ok("Deleted SUccessfully.");
                }).orElse(ResponseEntity.notFound().build());

    }

    //search specification
    public Page<Holiday> search(String value, Integer page, Integer size) {

        Pageable pageable = PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 10
        );

            return holidayRepository.findAll((root, query, cb) -> {
                if (value == null || value.trim().isEmpty()) {
                    return cb.conjunction(); // returns all records
                }

                String lowerValue = "%" + value.toLowerCase() + "%";
                Predicate predicate = cb.disjunction();

                for (Field field : Holiday.class.getDeclaredFields()) {
                    if (field.getType().equals(String.class)) {
                        predicate = cb.or(predicate,
                                cb.like(cb.lower(root.get(field.getName())), lowerValue));
                    }
                }
                predicate = cb.or(predicate,
                        cb.like(root.get("date").as(String.class), "%" + value + "%"));

                return predicate;
            }, pageable);
        }
    }
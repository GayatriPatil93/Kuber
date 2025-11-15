package com.example.Kuber.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class EmployeeShift {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String name;
    private LocalDate shiftDate;
    private String shiftType;
    private LocalTime shiftStartTime;
    private LocalTime shiftEndTime;
    private String status;
    private Duration totalHours;
    private LocalTime breakStartTime;
    private LocalTime breakEndTime;
    private String assignedBy;
    private String description;

    // Constructors
    public EmployeeShift() {

    }
    public EmployeeShift(Long id, Long employeeId, String name, LocalDate shiftDate, String shiftType, LocalTime shiftStartTime, LocalTime shiftEndTime, String status, Duration totalHours, LocalTime breakStartTime, LocalTime breakEndTime, String assignedBy, String description) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.shiftDate = shiftDate;
        this.shiftType = shiftType;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.status = status;
        this.totalHours = totalHours;
        this.breakStartTime = breakStartTime;
        this.breakEndTime = breakEndTime;
        this.assignedBy = assignedBy;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDate shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public LocalTime getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(LocalTime shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public LocalTime getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(LocalTime shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Duration getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Duration totalHours) {
        this.totalHours = totalHours;
    }

    public LocalTime getBreakStartTime() {
        return breakStartTime;
    }

    public void setBreakStartTime(LocalTime breakStartTime) {
        this.breakStartTime = breakStartTime;
    }

    public LocalTime getBreakEndTime() {
        return breakEndTime;
    }

    public void setBreakEndTime(LocalTime breakEndTime) {
        this.breakEndTime = breakEndTime;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

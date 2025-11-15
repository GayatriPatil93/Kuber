package com.example.Kuber.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Holiday
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate date;
    private String shift;
    private String location;
    private String holidayType;
    private String createdBy;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    public enum ApprovalStatus {
        APPROVED,
        REJECTED
    }

    private String details;

    @Column(updatable = false)
    private LocalDateTime creationDate;

    // Automatically set creationDate before saving
    @PrePersist
    public void onCreate() {
        this.creationDate = LocalDateTime.now();
    }

    //constructors
    public Holiday() {

    }
    public Holiday(Long id, String name, String shift, LocalDate date, String location, String holidayType, String createdBy,  ApprovalStatus approvalStatus, String details) {
        this.id = id;
        this.name = name;
        this.shift = shift;
        this.date = date;
        this.location = location;
        this.holidayType = holidayType;
        this.createdBy = createdBy;
        this.approvalStatus = approvalStatus;
        this.details = details;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

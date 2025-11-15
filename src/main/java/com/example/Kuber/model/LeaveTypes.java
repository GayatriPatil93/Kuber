package com.example.Kuber.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LeaveTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String leavename;
    private String leaveType;
    private String leaveUnit;
    private String status;
    private String note;
    private Integer duration;
    private String createdBy;
    private String carryOver;
    private Long maxLeaves;
    private Long anualLimit;

    public LeaveTypes() {

    }

    public LeaveTypes(Long id,String leavename, String leaveType, String leaveUnit, String status, String note, Integer duration, String createdBy, String carryOver, Long maxLeaves, Long anualLimit) {
        this.id = id;
        this.leavename = leavename;
        this.leaveType = leaveType;
        this.leaveUnit = leaveUnit;
        this.status = status;
        this.note = note;
        this.duration = duration;
        this.createdBy = createdBy;
        this.carryOver = carryOver;
        this.maxLeaves = maxLeaves;
        this.anualLimit = anualLimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeavename() {
        return leavename;
    }

    public void setLeavename(String leavename) {
        this.leavename = leavename;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveUnit() {
        return leaveUnit;
    }

    public void setLeaveUnit(String leaveUnit) {
        this.leaveUnit = leaveUnit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCarryOver() {
        return carryOver;
    }

    public void setCarryOver(String carryOver) {
        this.carryOver = carryOver;
    }

    public Long getMaxLeaves() {
        return maxLeaves;
    }

    public void setMaxLeaves(Long maxLeaves) {
        this.maxLeaves = maxLeaves;
    }

    public Long getAnualLimit() {
        return anualLimit;
    }

    public void setAnualLimit(Long anualLimit) {
        this.anualLimit = anualLimit;
    }
}




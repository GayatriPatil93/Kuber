package com.example.Kuber.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class LeaveSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String leavePolicyName;
    private String LeaveType;
    private Integer duration;
    private Integer carryLimit;
    private Integer leaveQuota;

    @OneToOne
    private Department department;
    private String leaveApproval;
    private String notes;


    public LeaveSettings(){}

    public LeaveSettings(String leavePolicyName, String leaveType, Integer duration, Integer carryLimit, Integer leaveQuota, Department department, String leaveApproval, String notes) {
        this.leavePolicyName = leavePolicyName;
        LeaveType = leaveType;
        this.duration = duration;
        this.carryLimit = carryLimit;
        this.leaveQuota = leaveQuota;
        this.department = department;
        this.leaveApproval = leaveApproval;
        this.notes = notes;
    }

    public String getLeavePolicyName() {
        return leavePolicyName;
    }

    public void setLeavePolicyName(String leavePolicyName) {
        this.leavePolicyName = leavePolicyName;
    }

    public String getLeaveType() {
        return LeaveType;
    }

    public void setLeaveType(String leaveType) {
        LeaveType = leaveType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getCarryLimit() {
        return carryLimit;
    }

    public void setCarryLimit(Integer carryLimit) {
        this.carryLimit = carryLimit;
    }

    public Integer getLeaveQuota() {
        return leaveQuota;
    }

    public void setLeaveQuota(Integer leaveQuota) {
        this.leaveQuota = leaveQuota;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getLeaveApproval() {
        return leaveApproval;
    }

    public void setLeaveApproval(String leaveApproval) {
        this.leaveApproval = leaveApproval;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

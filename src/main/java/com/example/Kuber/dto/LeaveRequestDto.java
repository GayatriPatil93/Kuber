package com.example.Kuber.dto;

import com.example.Kuber.model.LeaveStatus;

import java.time.LocalDate;

public class LeaveRequestDto {
    private Long id;
    private Long empid;
    private String empname;
    private String department;
    private Long leaveTypeId;
    private String leaveTypeName;
    private LocalDate leaveFrom;
    private LocalDate leaveTo;
    private LeaveStatus status;
    private String durationType;
    private LocalDate requestedOn;
    private String reason;
    private String note;
    private LocalDate approvalDate;
    private Integer noOfDays;

    public LeaveRequestDto() {

    }

    public LeaveRequestDto(Long id, Long empid, String empname, String department, Long leaveTypeId, String leaveTypeName, LocalDate leaveFrom, LocalDate leaveTo, LeaveStatus status, String durationType, LocalDate requestedOn, String reason, String note, LocalDate approvalDate, Integer noOfDays) {
        this.id = id;
        this.empid = empid;
        this.empname = empname;
        this.department = department;
        this.leaveTypeId = leaveTypeId;
        this.leaveTypeName = leaveTypeName;
        this.leaveFrom = leaveFrom;
        this.leaveTo = leaveTo;
        this.status = status;
        this.durationType = durationType;
        this.requestedOn = requestedOn;
        this.reason = reason;
        this.note = note;
        this.approvalDate = approvalDate;
        this.noOfDays = noOfDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(Long leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
    }

    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    public LocalDate getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(LocalDate leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public LocalDate getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(LocalDate leaveTo) {
        this.leaveTo = leaveTo;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public String getDurationType() {
        return durationType;
    }

    public void setDurationType(String durationType) {
        this.durationType = durationType;
    }

    public LocalDate getRequestedOn() {
        return requestedOn;
    }

    public void setRequestedOn(LocalDate requestedOn) {
        this.requestedOn = requestedOn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Integer getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        this.noOfDays = noOfDays;
    }
}



package com.example.Kuber.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id")
    private Employees employees;

    @ManyToOne
    @JoinColumn(name = "leaveType_id")
    private LeaveTypes leaveTypes;
    private LocalDate leaveFrom;
    private LocalDate leaveTo;
    private Integer NoOfDays;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
    private String durationType;
    private LocalDate requestedOn;
    private String reason;
    private String  note;
    private String approvedBy;
    private LocalDate approvalDate;

    public LeaveRequest() {
    }

    public LeaveRequest(Long id, Employees employees, LeaveTypes leaveTypes, LocalDate leaveFrom, LocalDate leaveTo, Integer noOfDays, LeaveStatus status, String durationType, LocalDate requestedOn, String reason, String approvedBy, String note, LocalDate approvalDate) {
        this.id = id;
        this.employees = employees;
        this.leaveTypes = leaveTypes;
        this.leaveFrom = leaveFrom;
        this.leaveTo = leaveTo;
        this.NoOfDays = noOfDays;
        this.status = status;
        this.durationType = durationType;
        this.requestedOn = requestedOn;
        this.reason = reason;
        this.note = note;
        this.approvedBy = approvedBy;
        this.approvalDate = approvalDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public LeaveTypes getLeaveTypes() {
        return leaveTypes;
    }

    public void setLeaveTypes(LeaveTypes leaveTypes) {
        this.leaveTypes = leaveTypes;
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

    public Integer getNoOfDays() {
        return NoOfDays;
    }

    public void setNoOfDays(Integer noOfDays) {
        NoOfDays = noOfDays;
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

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDate getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDate approvalDate) {
        this.approvalDate = approvalDate;
    }

}

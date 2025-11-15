package com.example.Kuber.dto;

import com.example.Kuber.model.LeaveStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class LeaveReqStatusDTO {

    private Long leaveRequestId;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
    private String approvedBy;

    public LeaveReqStatusDTO() {
    }

    public LeaveReqStatusDTO(Long leaveRequestId, LeaveStatus status, String approvedBy) {
        this.leaveRequestId = leaveRequestId;
        this.status = status;
        this.approvedBy = approvedBy;
    }

    public Long getLeaveRequestId() {
        return leaveRequestId;
    }

    public void setLeaveRequestId(Long leaveRequestId) {
        this.leaveRequestId = leaveRequestId;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}

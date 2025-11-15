package com.example.Kuber.dto;

public class LeaveBalanceDTO {

    private String employee;
    private String leaveTypes;
    private Long previousBalance;
    private Long currentBalance;
    private Long totalBalance;
    private Long usedLeave;
    private Long acceptedLeave;
    private Long rejectedLeave;
    private Long expiredLeave;
    private Long carryOverBalance;

    public LeaveBalanceDTO() {
    }

    public LeaveBalanceDTO(String employee, String leaveTypes, Long previousBalance, Long currentBalance, Long totalBalance, Long usedLeave, Long acceptedLeave, Long rejectedLeave, Long expiredLeave, Long carryOverBalance) {
        this.employee = employee;
        this.leaveTypes = leaveTypes;
        this.previousBalance = previousBalance;
        this.currentBalance = currentBalance;
        this.totalBalance = totalBalance;
        this.usedLeave = usedLeave;
        this.acceptedLeave = acceptedLeave;
        this.rejectedLeave = rejectedLeave;
        this.expiredLeave = expiredLeave;
        this.carryOverBalance = carryOverBalance;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getLeaveTypes() {
        return leaveTypes;
    }

    public void setLeaveTypes(String leaveTypes) {
        this.leaveTypes = leaveTypes;
    }

    public Long getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(Long previousBalance) {
        this.previousBalance = previousBalance;
    }

    public Long getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Long currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Long getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Long totalBalance) {
        this.totalBalance = totalBalance;
    }

    public Long getUsedLeave() {
        return usedLeave;
    }

    public void setUsedLeave(Long usedLeave) {
        this.usedLeave = usedLeave;
    }

    public Long getAcceptedLeave() {
        return acceptedLeave;
    }

    public void setAcceptedLeave(Long acceptedLeave) {
        this.acceptedLeave = acceptedLeave;
    }

    public Long getRejectedLeave() {
        return rejectedLeave;
    }

    public void setRejectedLeave(Long rejectedLeave) {
        this.rejectedLeave = rejectedLeave;
    }

    public Long getExpiredLeave() {
        return expiredLeave;
    }

    public void setExpiredLeave(Long expiredLeave) {
        this.expiredLeave = expiredLeave;
    }

    public Long getCarryOverBalance() {
        return carryOverBalance;
    }

    public void setCarryOverBalance(Long carryOverBalance) {
        this.carryOverBalance = carryOverBalance;
    }

}

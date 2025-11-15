package com.example.Kuber.exception;

public class DuplicateLeaveBalanceException extends RuntimeException{
    public DuplicateLeaveBalanceException(String message){
        super(message);
    }
}

package com.example.Kuber.exception;

public class LeaveRequestAlreadyExistsException extends RuntimeException{
    public LeaveRequestAlreadyExistsException(String message){
        super(message);
    }
}

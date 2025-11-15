package com.example.Kuber.exception;

public class LeaveRequestAlreadyProcessedException extends RuntimeException {

    public LeaveRequestAlreadyProcessedException(String message){
        super(message);
    }
}

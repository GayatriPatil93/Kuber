package com.example.Kuber.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LeaveRequestAlreadyExistsException.class)
    public ResponseEntity<String> leaveRequestAlreadyExists(LeaveRequestAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LeaveRequestAlreadyProcessedException.class)
    public ResponseEntity<String> leaveRequestAlreadyProcessedException(LeaveRequestAlreadyProcessedException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(DuplicateLeaveBalanceException.class)
    public  ResponseEntity<String> duplicateLeaveBalanceException(DuplicateLeaveBalanceException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(ConstraintException.class)
    public ResponseEntity<String> constraintException(ConstraintException ex){
        String msg = ex.getMessage();
        if(msg.contains("a foreign key constraint fails")){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid Employee or Leave type. Please make sure both exists or not.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Constraint Violation: " +ex.getMessage());
    }

}

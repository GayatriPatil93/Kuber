package com.example.Kuber.dto;

import java.time.LocalDate;
import java.util.List;

public class AttendanceListDto {
    private LocalDate date;
    private String status;

    //Constructor
    public AttendanceListDto(LocalDate date, String status){
        this.date=date;
        this.status=status;
    }

    //getter and setter
    public LocalDate getDate(){
        return date;
    }
    public void setDate(LocalDate date){
        this.date=date;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
}

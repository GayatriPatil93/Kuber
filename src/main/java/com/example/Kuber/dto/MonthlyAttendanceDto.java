package com.example.Kuber.dto;

import java.util.List;


public class MonthlyAttendanceDto {
    private Integer year;
    private Integer month;
    private List<EmployeeAttendanceDto> Employees;

//constructors
    public MonthlyAttendanceDto(Integer year, Integer month, List<EmployeeAttendanceDto> Employees){
        this.year = year;
        this.month = month;
        this.Employees = Employees;
    }

    //getters and setters
    public Integer getYear(){
        return year;
    }
    public void setYear(Integer year){
        this.year=year;
    }
    public Integer getMonth(){
        return month;
    }
    public void setMonth(Integer month){
        this.month=month;
    }
    public List<EmployeeAttendanceDto> getEmployees(){
        return Employees;
    }
    public void setEmployees(List<EmployeeAttendanceDto> Employees){
        this.Employees=Employees;
    }
}

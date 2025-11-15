package com.example.Kuber.model;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship with Employees table
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employees employees;

    private LocalDate date; // for storing the attendance date

    private LocalTime firstIn;
    private LocalTime breakTime;
    private LocalTime lastOut;
    private String status;
    private Double totalHours; // store total worked hours as number
    private String shift;
    private Integer year;
    private String month;

    // Constructors
    public Attendance() {}

    public Attendance(Long id, Employees employees, LocalDate date, LocalTime firstIn, LocalTime breakTime, LocalTime lastOut, String status, Double totalHours, String shift, Integer year, String month
    ) {
        this.id = id;
        this.employees = employees;
        this.date = date;
        this.firstIn = firstIn;
        this.breakTime = breakTime;
        this.lastOut = lastOut;
        this.status = status;
        this.totalHours = totalHours;
        this.shift = shift;
        this.year = year;
        this.month = month;

    }

    // Automatically calculate total hours before save/update
    @PrePersist
    @PreUpdate
    public void calculateTotalHours() {
        if (firstIn != null && lastOut != null) {
            Duration totalDuration = Duration.between(firstIn, lastOut);

            // Subtract 1 hour if break time exists
            if (breakTime != null) {
                totalDuration = totalDuration.minusHours(1);
            }

            // Convert total minutes to decimal hours
            this.totalHours = totalDuration.toMinutes() / 60.0;
        } else {
            this.totalHours = 0.0;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employees getEmployee() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getFirstIn() {
        return firstIn;
    }

    public void setFirstIn(LocalTime firstIn) {
        this.firstIn = firstIn;
    }

    public LocalTime getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(LocalTime breakTime) {
        this.breakTime = breakTime;
    }

    public LocalTime getLastOut() {
        return lastOut;
    }

    public void setLastOut(LocalTime lastOut) {
        this.lastOut = lastOut;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


}


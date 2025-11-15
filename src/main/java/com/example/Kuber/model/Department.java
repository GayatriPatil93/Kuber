package com.example.Kuber.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String HeadofDept;
    private String mobileNumber;
    private String email;
    private Long employeeCapacity;
    private Year establishedYear;
    private Long totalEmployees;


    public Department() {
    }

    public Department(Long id, String name, String headofDept, String mobileNumber, String email, Long employeeCapacity, Year establishedYear, Long totalEmployees) {
        this.id = id;
        this.name = name;
        this.HeadofDept = headofDept;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.employeeCapacity = employeeCapacity;
        this.establishedYear = establishedYear;
        this.totalEmployees = totalEmployees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadofDept() {
        return HeadofDept;
    }

    public void setHeadofDept(String headofDept) {
        HeadofDept = headofDept;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEmployeeCapacity() {
        return employeeCapacity;
    }

    public void setEmployeeCapacity(Long employeeCapacity) {
        this.employeeCapacity = employeeCapacity;
    }

    public Year getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(Year establishedYear) {
        this.establishedYear = establishedYear;
    }

    public Long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public void incrementEmp(){
        this.totalEmployees++;
    }
}

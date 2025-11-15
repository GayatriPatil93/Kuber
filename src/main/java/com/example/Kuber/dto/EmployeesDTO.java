package com.example.Kuber.dto;

import com.example.Kuber.model.Department;

import java.time.LocalDate;

public class EmployeesDTO {
    private String name;
    private String gender;
    private String mobile;
    private String designation;
    private String departmentname;
    private String email;
    private LocalDate dateOfBirth;
    private String education;
    private Double salary;
    private String image;

    public EmployeesDTO() {

    }

    public EmployeesDTO(String name, String gender, String mobile, String designation, String departmentname, String email, LocalDate dateOfBirth, String education, Double salary, String image) {
        this.name = name;
        this.gender = gender;
        this.mobile = mobile;
        this.designation = designation;
        this.departmentname = departmentname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.education = education;
        this.salary = salary;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

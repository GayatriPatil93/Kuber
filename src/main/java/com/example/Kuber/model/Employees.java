package com.example.Kuber.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private String mobile;
    private String password;
    private String re_enterpassword;
    private String designation;
    @ManyToOne
    private Department department;
    private String address;
    private String email;
    private LocalDate dateOfBirth;
    private String education;
    private Double salary;
    private String image;

    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendanceList= new ArrayList<>();


    private boolean active = true;

    public Employees() {
    }

    public Employees(Long id, String firstName, String lastName, String gender, String mobile, String password, String re_enterpassword, String designation, Department department, String address, String email, LocalDate dateOfBirth, String education, String image, Double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.mobile = mobile;
        this.password = password;
        this.re_enterpassword = re_enterpassword;
        this.designation = designation;
        this.department = department;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.education = education;
        this.image = image;
        this.salary = salary;
    }

    // Getters and Setters

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRe_enterpassword() {
        return re_enterpassword;
    }

    public void setRe_enterpassword(String re_enterpassword) {
        this.re_enterpassword = re_enterpassword;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}

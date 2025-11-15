package com.example.Kuber.events;

import com.example.Kuber.model.Employees;

public class EmployeeCreatedEvent {

    private final Employees employees;

    public EmployeeCreatedEvent(Employees employees) {
        this.employees = employees;
    }

    public Employees getEmployees(){
        return employees;
    }
}

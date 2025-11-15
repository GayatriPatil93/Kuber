package com.example.Kuber.controller;

import com.example.Kuber.model.Employees;
import com.example.Kuber.repository.EmployeesRepository;
import com.example.Kuber.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeesRepository employeesRepository;

    @Autowired
    private EmployeesService employeesService;

    //Add New Employee
    @PostMapping("/add")
    public ResponseEntity<String> addNewEmployee(@RequestBody Employees employees) {
        employeesService.addEmployee(employees);
        return ResponseEntity.ok("New Employee Added Successfully");
    }

    //Get Employee by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Employees> getEmployeeById(@PathVariable Long id) {
        return employeesService.getEmployeebyId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Get All Employees with Pagination
    @GetMapping("/getallemployees")
    public ResponseEntity<?> getAllEmployees(
            @RequestParam (required = false) Integer page,
            @RequestParam (required = false) Integer size){
                return ResponseEntity.ok(employeesService.getallEmployees(page, size)) ;
    }

    //Update Employee Details
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Employees updatedEmployee) {
    return employeesService.editEmp(id,updatedEmployee);


}
    //Delete Employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Long id) {
        return employeesService.deleteEmp(id);

    }
    //search using specification jpa
    @GetMapping("/search")
    public Page<Employees> searchByAnyField(
            @RequestParam String value,
            @RequestParam (required = false) Integer page,
            @RequestParam (required = false) Integer size
    ) {
        return employeesService.searchByAnyField(value,page,size);
    }


}


package com.example.Kuber.service;

import com.example.Kuber.dto.EmployeesDTO;
import com.example.Kuber.events.EmployeeCreatedEvent;
import com.example.Kuber.model.Department;
import com.example.Kuber.model.Employees;
import com.example.Kuber.model.LeaveBalance;
import com.example.Kuber.model.LeaveTypes;
import com.example.Kuber.repository.DepartmentRepository;
import com.example.Kuber.repository.EmployeesRepository;
import com.example.Kuber.repository.LeaveTypesRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeesService {

    private final EmployeesRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final LeaveTypesRepository leaveTypesRepository;
    private final ApplicationEventPublisher publisher;

    //Constructor Injection
    public EmployeesService(EmployeesRepository employeeRepository, DepartmentRepository departmentRepository, LeaveTypesRepository leaveTypesRepository, ApplicationEventPublisher publisher) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.leaveTypesRepository = leaveTypesRepository;
        this.publisher = publisher;
    }

    //Add emp
    public Employees addEmployee(Employees employees){

        Department dept = departmentRepository.findById(employees.getDepartment().getId())
                .orElseThrow(()-> new RuntimeException("Department Not Found"));
        dept.incrementEmp();
        departmentRepository.save(dept);
        Employees emp = employeeRepository.save(employees);

         //publish event here
        publisher.publishEvent(new EmployeeCreatedEvent(emp));
        return emp;


    }
    //Fetch All
    public Object getallEmployees(Integer page , Integer size){
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size);
            return employeeRepository.findAll(pageable).stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        else {
            return employeeRepository.findAll();
        }
    }
    //Fetch By ID
    public Optional<Employees> getEmployeebyId(Long id){
        return employeeRepository.findById(id);
    }
    //Edit
    public ResponseEntity<String> editEmp(Long id, Employees updatedEmployee) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setFirstName(updatedEmployee.getFirstName());
                    employee.setLastName(updatedEmployee.getLastName());
                    employee.setGender(updatedEmployee.getGender());
                    employee.setMobile(updatedEmployee.getMobile());
                    employee.setPassword(updatedEmployee.getPassword());
                    employee.setRe_enterpassword(updatedEmployee.getRe_enterpassword());
                    employee.setDesignation(updatedEmployee.getDesignation());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setAddress(updatedEmployee.getAddress());
                    employee.setEmail(updatedEmployee.getEmail());
                    employee.setDateOfBirth(updatedEmployee.getDateOfBirth());
                    employee.setEducation(updatedEmployee.getEducation());
                    employee.setImage(updatedEmployee.getImage());
                    employee.setSalary(updatedEmployee.getSalary());
                    employee.setActive(updatedEmployee.isActive());

                    employeeRepository.save(employee);
                    return ResponseEntity.ok("Employee Details Updated Successfully");
                })
                .orElse(ResponseEntity.notFound().build());
    }
    //Delete Employee
    public ResponseEntity<String> deleteEmp(Long id) {
        Employees emp = employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("Employee not found.")) ;
        Department dept = emp.getDepartment();
        dept.setTotalEmployees(dept.getTotalEmployees() -1);
        departmentRepository.save(dept);
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return ResponseEntity.ok("Employee Deleted Successfully");
                }).orElse(ResponseEntity.notFound().build());
    }

    //specification for search

    public Page<Employees> searchByAnyField(String value, Integer page, Integer size) {
        if ((page!=null) && (size!=null)){
            Pageable pageable = PageRequest.of(page,size);
            return employeeRepository.findAll(pageable);
        }
        return (Page<Employees>) employeeRepository.findAll((root, query, cb) -> {
            if (value == null || value.trim().isEmpty()) {
                return cb.conjunction();
            }
            String pattern = "%" + value.toLowerCase() + "%";
            Predicate firstName = cb.like(cb.lower(root.get("firstName")), pattern);
            Predicate lastName = cb.like(cb.lower(root.get("lastName")), pattern);
            Predicate gender = cb.like(cb.lower(root.get("gender")), pattern);
            Predicate mobile = cb.like(cb.lower(root.get("mobile")), pattern);
            Predicate designation = cb.like(cb.lower(root.get("designation")), pattern);
            Predicate department = cb.like(cb.lower(root.get("department")), pattern);
            Predicate address = cb.like(cb.lower(root.get("address")), pattern);
            Predicate email = cb.like(cb.lower(root.get("email")), pattern);
            Predicate education = cb.like(cb.lower(root.get("education")), pattern);
            Predicate dateOfBirth = cb.like(root.get("dateOfBirth").as(String.class), "%" + value + "%");
            Predicate salary = cb.like(root.get("salary").as(String.class), "%" + value + "%");

            return cb.or(firstName, lastName,gender,mobile, designation, department,address,email,dateOfBirth,education,salary );
        });
    }

    public EmployeesDTO convertToDto(Employees employees){
        EmployeesDTO dto = new EmployeesDTO();
        dto.setName(employees.getFirstName()+ " "+employees.getLastName());
        dto.setGender(employees.getGender());
        dto.setDesignation(employees.getDesignation());
        dto.setEducation(employees.getEducation());
        dto.setEmail(employees.getEmail());
        dto.setDateOfBirth(employees.getDateOfBirth());
        dto.setMobile(employees.getMobile());
        dto.setSalary(employees.getSalary());
        dto.setImage(employees.getImage());

        if(employees.getDepartment()!= null){
            dto.setDepartmentname(employees.getDepartment().getName());
        }

        return dto;
    }

}


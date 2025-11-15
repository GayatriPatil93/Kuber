package com.example.Kuber.controller;

import com.example.Kuber.model.Department;
import com.example.Kuber.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    //Add Department
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }
    //edit department
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id, @RequestBody Department updateddepartment){
        return departmentService.editDepartment(id,updateddepartment);
    }
    //get all
    @GetMapping("/get")
    public Page<Department> getall(
            @RequestParam (required = false) Integer page,
            @RequestParam (required = false) Integer size
    ){
        return departmentService.getall(page,size);
    }

    //delete department
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
       return departmentService.delete(id);

    }

}

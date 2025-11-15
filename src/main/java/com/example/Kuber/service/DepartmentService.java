package com.example.Kuber.service;

import com.example.Kuber.model.Department;
import com.example.Kuber.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

        public ResponseEntity<String> addDepartment(Department department){
         departmentRepository.save(department);
         return ResponseEntity.status(HttpStatus.CREATED).body("Added Department Successfully.");
    }

        public ResponseEntity<String> editDepartment(Long id, Department updatedDepartment){
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(updatedDepartment.getName());
                    department.setEmail(updatedDepartment.getEmail());
                    department.setHeadofDept(updatedDepartment.getHeadofDept());
                    department.setMobileNumber(updatedDepartment.getMobileNumber());
                    department.setEmployeeCapacity(updatedDepartment.getEmployeeCapacity());
                    department.setEstablishedYear(updatedDepartment.getEstablishedYear());
                    department.setTotalEmployees(updatedDepartment.getTotalEmployees());

                    departmentRepository.save(department);
                    return ResponseEntity.ok("Department Edited...");
                }).orElse(ResponseEntity.notFound().build());
        }

        public ResponseEntity<String > delete(Long id){
        return departmentRepository.findById(id)
                .map(department -> {
                    departmentRepository.delete(department);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Department deleted..");
                }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found."));

        }

        public Page<Department> getall(Integer page, Integer size){
            if (page!=null && size!=null) {
                Pageable pageable = PageRequest.of(page,size);
                return departmentRepository.findAll(pageable);
            }
            else {
                return (Page<Department>) departmentRepository.findAll();
            }

        }

}

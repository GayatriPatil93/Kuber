package com.example.Kuber.repository;

import com.example.Kuber.model.Employees;
import com.example.Kuber.model.LeaveRequest;
import com.example.Kuber.model.LeaveTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> , JpaSpecificationExecutor {

    boolean existsByEmployeesAndLeaveFromAndLeaveTo(
            Employees employees,
            LocalDate leaveFrom,
            LocalDate leaveTo);


}
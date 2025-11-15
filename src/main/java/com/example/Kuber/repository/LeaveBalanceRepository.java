package com.example.Kuber.repository;

import com.example.Kuber.model.Employees;
import com.example.Kuber.model.LeaveBalance;
import com.example.Kuber.model.LeaveTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> , JpaSpecificationExecutor {

    LeaveBalance findByEmployeesAndLeaveTypes(Employees employees, LeaveTypes leaveTypes);

    boolean existsByEmployeesAndLeaveTypes(Employees employees, LeaveTypes leaveTypes);
}


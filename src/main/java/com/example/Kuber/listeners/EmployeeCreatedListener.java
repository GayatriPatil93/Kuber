package com.example.Kuber.listeners;

import com.example.Kuber.events.EmployeeCreatedEvent;
import com.example.Kuber.model.Employees;
import com.example.Kuber.model.LeaveBalance;
import com.example.Kuber.model.LeaveTypes;
import com.example.Kuber.repository.LeaveBalanceRepository;
import com.example.Kuber.repository.LeaveTypesRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeCreatedListener {

    private final LeaveTypesRepository leaveTypesRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;


    public EmployeeCreatedListener(LeaveTypesRepository leaveTypesRepository, LeaveBalanceRepository leaveBalanceRepository) {
        this.leaveTypesRepository = leaveTypesRepository;
        this.leaveBalanceRepository = leaveBalanceRepository;
    }

    @EventListener
    public void handledEmployeeCreated(EmployeeCreatedEvent event){
        Employees emp = event.getEmployees();

        List<LeaveTypes> leaveTypes = leaveTypesRepository.findAll();

        for(LeaveTypes lt :leaveTypes){
            LeaveBalance leaveBalance = new LeaveBalance();

            leaveBalance.setEmployees(emp);
            leaveBalance.setLeaveTypes(lt);
            leaveBalance.setPreviousBalance(0L);
            leaveBalance.setCurrentBalance(lt.getMaxLeaves());
            leaveBalance.setTotalBalance(lt.getMaxLeaves());
            leaveBalance.setUsedLeave(0L);
            leaveBalance.setAcceptedLeave(0L);
            leaveBalance.setRejectedLeave(0L);
            leaveBalance.setExpiredLeave(0L);
            leaveBalance.setCarryOverBalance(5L);

            leaveBalanceRepository.save(leaveBalance);
        }
        System.out.println("leaveBalance is created for employee: " +emp.getId());
    }

}

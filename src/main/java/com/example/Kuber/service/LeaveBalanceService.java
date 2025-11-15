package com.example.Kuber.service;

import com.example.Kuber.dto.LeaveBalanceDTO;
import com.example.Kuber.exception.ConstraintException;
import com.example.Kuber.exception.DuplicateLeaveBalanceException;
import com.example.Kuber.model.Employees;
import com.example.Kuber.model.LeaveBalance;
import com.example.Kuber.model.LeaveTypes;
import com.example.Kuber.repository.EmployeesRepository;
import com.example.Kuber.repository.LeaveBalanceRepository;
import com.example.Kuber.repository.LeaveTypesRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveBalanceService
{
    @Autowired
    private  LeaveBalanceRepository leaveBalanceRepository;
    @Autowired
    private EmployeesRepository employeesRepository;
    @Autowired
    private LeaveTypesRepository leaveTypesRepository;


    public ResponseEntity<String> addLeaveBalance(LeaveBalance leaveBalance){

        boolean exists = leaveBalanceRepository.existsByEmployeesAndLeaveTypes(leaveBalance.getEmployees(),leaveBalance.getLeaveTypes());
        if (exists){
            throw new DuplicateLeaveBalanceException("LeaveBalance already exixts for this ID.");
        }

        Long employeesId = leaveBalance.getEmployees().getId();
        boolean employeeExists = employeesRepository.existsById(employeesId);
        if (!employeeExists){
            throw new ConstraintException("Employee Not found");
        }

        Long leaveTypesId = leaveBalance.getLeaveTypes().getId();
        boolean leaveTypesExists = leaveTypesRepository.existsById(leaveTypesId);
        if (!leaveTypesExists){
            throw new ConstraintException("LeaveType not found");
        }

        leaveBalanceRepository.save(leaveBalance);
        return ResponseEntity.status(HttpStatus.CREATED).body("LeaveBalance Added Successfully.");
    }

    public Optional<ResponseEntity<String>> updateLeaveBalance(Long id, LeaveBalance updatedleaveBalance) {
        return leaveBalanceRepository.findById(id)
                .map(leaveBalance -> {
                    leaveBalance.setEmployees(updatedleaveBalance.getEmployees());
                    leaveBalance.setLeaveTypes(updatedleaveBalance.getLeaveTypes());
                    leaveBalance.setAcceptedLeave(updatedleaveBalance.getAcceptedLeave());
                    leaveBalance.setCurrentBalance(updatedleaveBalance.getCurrentBalance());
                    leaveBalance.setPreviousBalance(updatedleaveBalance.getPreviousBalance());
                    leaveBalance.setTotalBalance(updatedleaveBalance.getTotalBalance());
                    leaveBalance.setExpiredLeave(updatedleaveBalance.getExpiredLeave());
                    leaveBalance.setUsedLeave(updatedleaveBalance.getUsedLeave());
                    leaveBalance.setRejectedLeave(updatedleaveBalance.getRejectedLeave());
                    leaveBalance.setUsedLeave(updatedleaveBalance.getUsedLeave());
                    leaveBalance.setCarryOverBalance(updatedleaveBalance.getCarryOverBalance());

                    leaveBalanceRepository.save(leaveBalance);
                    return ResponseEntity.ok("LeaveBalance Updated...");
                });
    }

        public ResponseEntity<String> deleteBalance(Long id) {
        return leaveBalanceRepository.findById(id)
                .map(leaveBalance -> {
                    leaveBalanceRepository.deleteById(id);
                    return ResponseEntity.ok("Deleted Successfully..");
                }).orElse(ResponseEntity.notFound().build());

    }

    private LeaveBalanceDTO convertTodto(LeaveBalance balance) {
        LeaveBalanceDTO dto = new LeaveBalanceDTO();

        if (balance.getEmployees() != null) {
            dto.setEmployee(balance.getEmployees().getFirstName() + " " + balance.getEmployees().getLastName());
        }
        if (balance.getLeaveTypes() != null){
            dto.setLeaveTypes(balance.getLeaveTypes().getLeavename());
        }

        dto.setAcceptedLeave(balance.getAcceptedLeave());
        dto.setCurrentBalance(balance.getCurrentBalance());
        dto.setPreviousBalance(balance.getPreviousBalance());
        dto.setTotalBalance(balance.getTotalBalance());
        dto.setExpiredLeave(balance.getExpiredLeave());
        dto.setCarryOverBalance(balance.getCarryOverBalance());
        dto.setRejectedLeave(balance.getRejectedLeave());
        dto.setUsedLeave(balance.getUsedLeave());

        return dto;
    }

    public List<LeaveBalanceDTO> getall(Integer page, Integer size){
        List<LeaveBalance> leaveBalances;
        if( page != null && size!= null){
            Pageable pageable = PageRequest.of(page,size);

            leaveBalances =  leaveBalanceRepository.findAll(pageable).getContent();
        }
        else {
        leaveBalances =  leaveBalanceRepository.findAll();
    }
    List<LeaveBalanceDTO> dtoList = new ArrayList<>();
    for (LeaveBalance leaveBalance : leaveBalances){
        dtoList.add(convertTodto(leaveBalance));
    }
    return dtoList;

}

    public Page<LeaveBalanceDTO> search(String value , Integer page, Integer size){
         Pageable pageable = PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 10
        );
        Page<LeaveBalance> pageResult = leaveBalanceRepository.findAll((root, query, criteriaBuilder) -> {
            if (value == null || value.trim().isEmpty()) {
                return criteriaBuilder.conjunction(); // return all records
            }

            String searchvalue = "%"+value.toLowerCase().trim()+"%";
            Predicate predicate = criteriaBuilder.disjunction();

            Join<LeaveBalance, Employees> empJoin = root.join("employees", JoinType.LEFT);
            Join<LeaveBalance, LeaveTypes> leaveTypeJoin = root.join("leaveTypes", JoinType.LEFT);

            predicate = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(empJoin.get("firstName")), searchvalue),
                    criteriaBuilder.like(criteriaBuilder.lower(empJoin.get("lastName")), searchvalue),
                    criteriaBuilder.like(criteriaBuilder.lower(leaveTypeJoin.get("leavename")), searchvalue)
            );
            if( value.matches("\\d+")) {
                predicate = criteriaBuilder.or(predicate,
                        criteriaBuilder.like(root.get("previousBalance").as(String.class), "%" + value + "%"),
                        criteriaBuilder.like(root.get("currentBalance").as(String.class), "%" + value + "%"),
                        criteriaBuilder.like(root.get("totalBalance").as(String.class), "%" + value + "%"),
                        criteriaBuilder.like(root.get("usedLeave").as(String.class), "%" + value + "%"),
                        criteriaBuilder.like(root.get("acceptedLeave").as(String.class), "%" + value + "%"),
                        criteriaBuilder.like(root.get("rejectedLeave").as(String.class), "%" + value + "%"),
                        criteriaBuilder.like(root.get("expiredLeave").as(String.class), "%" + value + "%"),
                        criteriaBuilder.like(root.get("carryOverBalance").as(String.class), "%" + value + "%")
                );
            }

            return predicate;
        }, pageable);

        List<LeaveBalanceDTO> dtoList = pageResult.getContent()
                .stream()
                .map(this::convertTodto)
                .toList();

        return new PageImpl<>(dtoList, pageable, pageResult.getTotalElements());
    }

}

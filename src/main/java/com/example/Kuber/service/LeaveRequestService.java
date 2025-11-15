package com.example.Kuber.service;

import com.example.Kuber.dto.LeaveReqStatusDTO;
import com.example.Kuber.dto.LeaveRequestDto;
import com.example.Kuber.exception.ConstraintException;
import com.example.Kuber.exception.LeaveRequestAlreadyExistsException;
import com.example.Kuber.exception.LeaveRequestAlreadyProcessedException;
import com.example.Kuber.model.*;
import com.example.Kuber.repository.EmployeesRepository;
import com.example.Kuber.repository.LeaveBalanceRepository;
import com.example.Kuber.repository.LeaveRequestRepository;
import com.example.Kuber.repository.LeaveTypesRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LeaveRequestService {

    private final EmployeesRepository employeesRepository;
    private final LeaveTypesRepository leaveTypesRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;

    public LeaveRequestService(EmployeesRepository employeesRepository, LeaveTypesRepository leaveTypesRepository, LeaveRequestRepository leaveRequestRepository, LeaveBalanceRepository leaveBalanceRepository) {
        this.employeesRepository = employeesRepository;
        this.leaveTypesRepository = leaveTypesRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.leaveBalanceRepository = leaveBalanceRepository;
    }

    public ResponseEntity<String> addRequest(LeaveRequest leaveRequest) {

        Employees emp = employeesRepository.findById(leaveRequest.getEmployees().getId()).orElseThrow(() -> new RuntimeException("Employee not found."));
        // check if already leave request for same from and todate
        // if found then not allowed to create leave request
        LeaveTypes type = leaveTypesRepository.findById(leaveRequest.getLeaveTypes().getId()).orElseThrow(() -> new RuntimeException("Type not found."));

        boolean exists = leaveRequestRepository.existsByEmployeesAndLeaveFromAndLeaveTo(emp, leaveRequest.getLeaveFrom(), leaveRequest.getLeaveTo());
        if (exists) {
            throw new LeaveRequestAlreadyExistsException("Leave already exists for this dates.");
        }

        leaveRequest.setEmployees(emp);
        leaveRequest.setLeaveTypes(type);


        leaveRequestRepository.save(leaveRequest);
        return ResponseEntity.ok("Request Created Successfully.");
    }

    //fetch all
    private LeaveRequestDto convertTodto(LeaveRequest request) {
        LeaveRequestDto dto = new LeaveRequestDto();
        dto.setId(request.getId());

        if (request.getEmployees() != null) {
            dto.setEmpid(request.getEmployees().getId());
            dto.setEmpname(request.getEmployees().getFirstName() + " " + request.getEmployees().getLastName());
        }

        if (request.getLeaveTypes() != null) {
            dto.setLeaveTypeId(request.getLeaveTypes().getId());
            dto.setLeaveTypeName(request.getLeaveTypes().getLeavename());
        }
        if (request.getEmployees().getDepartment() != null) {
            dto.setDepartment(request.getEmployees().getDepartment().getName());

        }
        dto.setLeaveFrom(request.getLeaveFrom());
        dto.setLeaveTo(request.getLeaveTo());
        dto.setDurationType(request.getDurationType());
        dto.setStatus(LeaveStatus.valueOf(String.valueOf(request.getStatus())));
        dto.setDurationType(request.getDurationType());
        dto.setRequestedOn(LocalDate.now());
        dto.setReason(request.getReason());
        dto.setNote(request.getNote());
        dto.setApprovalDate(request.getApprovalDate());
        Long days = ChronoUnit.DAYS.between(request.getLeaveFrom(), request.getLeaveTo());
        dto.setNoOfDays(Math.toIntExact(days));
        return dto;
    }

    public List<LeaveRequestDto> getall(Integer page, Integer size) {
        List<LeaveRequest> leaveRequests;

        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size);
            leaveRequests = leaveRequestRepository.findAll(pageable).getContent();
        } else {
            leaveRequests = leaveRequestRepository.findAll();
        }

        List<LeaveRequestDto> dtoList = new ArrayList<>();
        for (LeaveRequest request : leaveRequests) {
            dtoList.add(convertTodto(request));
        }

        return dtoList;
    }


    public Optional<ResponseEntity<String>> editRequest(Long id, LeaveRequest updatedLeaveRequest) {
        return leaveRequestRepository.findById(id)
                .map(leaveRequest -> {
                    leaveRequest.setEmployees(updatedLeaveRequest.getEmployees());
                    leaveRequest.setLeaveFrom(updatedLeaveRequest.getLeaveFrom());
                    leaveRequest.setLeaveTo(updatedLeaveRequest.getLeaveTo());
                    leaveRequest.setLeaveTypes(updatedLeaveRequest.getLeaveTypes());
                    leaveRequest.setNoOfDays(updatedLeaveRequest.getNoOfDays());
                    leaveRequest.setRequestedOn(updatedLeaveRequest.getRequestedOn());
                    leaveRequest.setNote(updatedLeaveRequest.getNote());
                    leaveRequest.setApprovalDate(updatedLeaveRequest.getApprovalDate());
                    leaveRequest.setDurationType(updatedLeaveRequest.getDurationType());
                    leaveRequest.setReason(updatedLeaveRequest.getReason());
                    leaveRequest.setStatus(updatedLeaveRequest.getStatus());
                    leaveRequestRepository.save(leaveRequest);
                    return ResponseEntity.ok("Request Updated.");
                });
    }

    public ResponseEntity<String> delete(Long id) {
        return leaveRequestRepository.findById(id)
                .map(leaveRequest -> {
                    leaveRequestRepository.delete(leaveRequest);
                    return ResponseEntity.ok("Request Deleted...");
                }).orElse(ResponseEntity.notFound().build());
    }

    //specification logic
    public Page<LeaveRequest> search(String value, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(
                page != null ? page : 0,
                size != null ? size : 10
        );

        return leaveRequestRepository.findAll((root, query, cb) -> {
            if (value == null || value.trim().isEmpty()) {
                return cb.conjunction(); // returns all records
            }

            String lowerValue = "%" + value.toLowerCase() + "%";
            Predicate predicate = cb.disjunction();

            for (Field field : LeaveRequest.class.getDeclaredFields()) {
                if (field.getType().equals(String.class)) {
                    predicate = cb.or(predicate,
                            cb.like(cb.lower(root.get(field.getName())), lowerValue));
                }
            }
            predicate = cb.or(predicate,
                    cb.like(root.get("leaveFrom").as(String.class), "%" + value + "%"),
                    cb.like(root.get("leaveTo").as(String.class), "%" + value + "%"),
                    cb.like(root.get("leaveRequestedOn").as(String.class), "%" + value + "%"),
                    cb.like(root.get("leaveApprovalDate").as(String.class), "%" + value + "%")
            );

            return predicate;
        }, pageable);
    }


    public ResponseEntity<String> approveOrRejectLeave(LeaveReqStatusDTO dto) {
        LeaveRequest request = leaveRequestRepository.findById(dto.getLeaveRequestId()).orElseThrow(()->
                new ConstraintException("Leave Request Not Found"));

        String currentStatus = request.getStatus() != null ? request.getStatus().toString().toUpperCase() : "PENDING";
        if (currentStatus.equals("APPROVED") || currentStatus.equals("REJECTED")) {
            throw new LeaveRequestAlreadyProcessedException(
                    "This request already " + request.getStatus().toString().toLowerCase() + ". So, it cannot be modified.");
        }

        if (LocalDate.now().isAfter(request.getLeaveTo())) {
            throw new LeaveRequestAlreadyProcessedException(
                    "Cannot modified request after its end date."
            );
        }
        request.setStatus(dto.getStatus());
        request.setApprovedBy(dto.getApprovedBy());
        request.setApprovalDate(LocalDate.now());
        leaveRequestRepository.save(request);

        Employees employees = request.getEmployees();
        LeaveTypes leaveTypes = request.getLeaveTypes();
        LeaveBalance leaveBalance = leaveBalanceRepository.findByEmployeesAndLeaveTypes(employees, leaveTypes);

        if (dto.getStatus().equals(LeaveStatus.APPROVED)) {
            leaveBalance.setAcceptedLeave(leaveBalance.getAcceptedLeave() + 1);
            leaveBalance.setUsedLeave(leaveBalance.getAcceptedLeave());
            leaveBalance.setCurrentBalance(leaveBalance.getCurrentBalance() - leaveBalance.getUsedLeave());
            leaveBalance.setTotalBalance(leaveBalance.getTotalBalance() - leaveBalance.getUsedLeave());

        } else if (dto.getStatus().equals(LeaveStatus.REJECTED)) {
            leaveBalance.setRejectedLeave(leaveBalance.getRejectedLeave()+1);

        }
        leaveBalanceRepository.save(leaveBalance);
        return ResponseEntity.ok("Leave " + dto.getStatus() + " Successfully..!");

    }
}

package com.example.Kuber.service;

import com.example.Kuber.dto.AttendanceListDto;
import com.example.Kuber.dto.EmployeeAttendanceDto;
import com.example.Kuber.dto.MonthlyAttendanceDto;
import com.example.Kuber.model.Attendance;
import com.example.Kuber.model.Employees;
import com.example.Kuber.model.Holiday;
import com.example.Kuber.repository.AttendanceRepository;
import com.example.Kuber.repository.EmployeesRepository;
import com.example.Kuber.repository.HolidayRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    private final EmployeesRepository employeesRepository;

    private final HolidayRepository holidayRepository;


    public AttendanceService(AttendanceRepository attendanceRepository, EmployeesRepository employeesRepository, HolidayRepository holidayRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeesRepository = employeesRepository;
        this.holidayRepository = holidayRepository;
    }

    public ResponseEntity<String> addEmpAttendance( Attendance attendance){
         attendanceRepository.save(attendance);
        return ResponseEntity.ok("Employee Attendance Marked Successfully");

    }

    public ResponseEntity<?> getTodaysAttendance(Integer page, Integer size) {
        if (page != null && size != null) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Attendance> employeePage = attendanceRepository.findAll(pageable);
            return ResponseEntity.ok(employeePage);
        } else {
            return ResponseEntity.ok(attendanceRepository.findAll());
        }
    }
    public ResponseEntity<Attendance> getAttendanceById(Long id){
        return attendanceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
    // Fetch Attendance details of particular year and month
        public MonthlyAttendanceDto getMonthlyAttendance(int year, int month) {

            List<Attendance> attendanceList = attendanceRepository.findByYearAndMonth(year, month);
            List<Employees> allEmployees = employeesRepository.findAll();
            List<Holiday> holidayList = holidayRepository.findAll();

            Set<LocalDate> holidayDates = holidayList.stream()

                    .map(Holiday::getDate)
                    .collect(Collectors.toSet());

            Map<Long, List<Attendance>> groupedByEmployee = attendanceList.stream()
                    .collect(Collectors.groupingBy(a -> a.getEmployee().getId()));

            List<EmployeeAttendanceDto> employeeDtos = new ArrayList<>();
            int daysInMonth = java.time.YearMonth.of(year, month).lengthOfMonth();

            for (Employees emp : allEmployees) {
                List<Attendance> empAttendanceList  = groupedByEmployee.get(emp.getId());
                List<AttendanceListDto> attendancelist = new ArrayList<>();

                for (int day = 1; day <= daysInMonth; day++) {
                    LocalDate date = LocalDate.of(year, month, day);
                    String status = "Absent"; //leave / absent

                    DayOfWeek dayOfWeek = date.getDayOfWeek();
                    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                        status = "Weekend"; // Weekend
                    }
                    if (holidayDates.contains(date)) {
                        status = "Holiday";//holiday
                    }
                    if (empAttendanceList != null) {
                        for (Attendance att : empAttendanceList) {
                            if (att.getDate().equals(date) && "PRESENT".equalsIgnoreCase(att.getStatus())) {
                                status = "Present"; //present
                            }
                                break;
                            }
                        }
                    attendancelist.add(new AttendanceListDto(date, status));
                }
                employeeDtos.add(new EmployeeAttendanceDto(
                        emp.getId(),
                        emp.getFirstName(),
                        attendancelist
                ));
            }

            return new MonthlyAttendanceDto(year, month, employeeDtos);

    }

    public ResponseEntity<String> updateAttendance( Long id, Attendance updatedAttendance) {
        return attendanceRepository.findById(id)
                .map(attendance -> {
                    attendance.setFirstIn(updatedAttendance.getFirstIn());
                    attendance.setBreakTime(updatedAttendance.getBreakTime());
                    attendance.setLastOut(updatedAttendance.getLastOut());
                    attendance.setStatus(updatedAttendance.getStatus());
                    attendance.setShift(updatedAttendance.getShift());
                    attendance.setTotalHours(updatedAttendance.getTotalHours());
                    attendanceRepository.save(attendance);
                    return ResponseEntity.ok("Attendance Updated Successfully");
                }).orElse(ResponseEntity.notFound().build());
    }
     public ResponseEntity<String> deleteAttendance( Long id) {
        return attendanceRepository.findById(id)
                .map(attendance -> {
                    attendanceRepository.delete(attendance);
                    return ResponseEntity.ok("Attendance Deleted Successfully");
                }).orElse(ResponseEntity.notFound().build());
    }

    public List<Attendance> getByYearAndMonth( Integer year,  Integer month) {
        return attendanceRepository.findByYearAndMonth(year, month);
    }

    public void generateWeekendsForAllEmployees(int year, int month) {
        List<Employees> allEmployees = employeesRepository.findAll();
        YearMonth yearMonth = YearMonth.of(year, month);

    }
    //search specification
    public Page<Attendance> search(String value, Integer page, Integer size){
        Pageable pageable = PageRequest.of(
                page != null ? page:0,
                size != null ? size:10
        );
        return attendanceRepository.findAll((root, query, criteriaBuilder) ->
        {
            if (value == null || value.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String lowervalue = "% "+ value.toLowerCase() +"%";
            Predicate predicate = criteriaBuilder.disjunction();

            for( Field f : Attendance.class.getDeclaredFields()){
                if( f.getType().equals(String.class)){
                    predicate = criteriaBuilder.or(predicate,
                            criteriaBuilder.like(criteriaBuilder.lower(root.get(f.getName())),lowervalue));
                }
                predicate = criteriaBuilder.or(predicate,
                        criteriaBuilder.like(root.get("date").as(String.class),"%"+ value + "%"));

                Join<Object, Object> employeeJoin = root.join("employees", JoinType.LEFT);
                 predicate =criteriaBuilder.or(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("firstName")), "%" + value.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("lastName")),lowervalue),
                        criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("gender")),lowervalue),
                        criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("mobile")).as(String.class),lowervalue),
                        criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("designation")),lowervalue),
                        criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("department")),lowervalue),
                        criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("address")),lowervalue),
                        criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("email")),lowervalue),
                        criteriaBuilder.like(criteriaBuilder.lower(employeeJoin.get("education")),lowervalue)
                        );
            }
                return predicate;
            }, pageable);

        }
}

package com.example.Kuber.repository;

import com.example.Kuber.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> , JpaSpecificationExecutor<Attendance> {

    @Query("SELECT a FROM Attendance a WHERE YEAR(a.date) = :year AND MONTH(a.date) = :month")
    List<Attendance> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    boolean existsByEmployeesIdAndDate(Long employee_id, LocalDate date);
}

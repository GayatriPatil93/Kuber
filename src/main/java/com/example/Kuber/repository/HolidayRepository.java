package com.example.Kuber.repository;

import com.example.Kuber.model.Holiday;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> , JpaSpecificationExecutor {
    List<Holiday> findByDateBetween(LocalDate startDate, LocalDate endDate);

}

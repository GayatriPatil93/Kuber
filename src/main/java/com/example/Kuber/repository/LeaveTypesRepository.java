package com.example.Kuber.repository;

import com.example.Kuber.model.LeaveTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypesRepository extends JpaRepository<LeaveTypes, Long> {
}

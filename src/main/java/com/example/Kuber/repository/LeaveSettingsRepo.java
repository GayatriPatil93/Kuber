package com.example.Kuber.repository;

import com.example.Kuber.model.LeaveSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveSettingsRepo extends JpaRepository<LeaveSettings,Long> {
}

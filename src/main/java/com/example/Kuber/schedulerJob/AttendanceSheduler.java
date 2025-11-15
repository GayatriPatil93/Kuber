package com.example.Kuber.schedulerJob;

import com.example.Kuber.service.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AttendanceSheduler {

    private static final Logger log = LoggerFactory.getLogger(AttendanceSheduler.class);
    private final AttendanceService attendanceService;

    public AttendanceSheduler(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    //Run 1am at first day of every month
    @Scheduled(cron = "0 0 12 1 * ?")
    public void generateweekends() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        attendanceService.generateWeekendsForAllEmployees(year,month);
// fetch data from holiday table - current date
        // if data is available then add the holiday entry in the attendance table.
    }


}

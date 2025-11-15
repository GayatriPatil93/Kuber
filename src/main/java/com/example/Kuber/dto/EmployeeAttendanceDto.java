package com.example.Kuber.dto;

import java.util.List;

public class EmployeeAttendanceDto {
    private Long id;
    private String name;
    private List<AttendanceListDto> attendanceList;

    public EmployeeAttendanceDto(Long id, String name, List<AttendanceListDto> attendanceList) {
        this.id = id;
        this.name = name;
        this.attendanceList = attendanceList;
    }

    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttendanceListDto> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendance(List<AttendanceListDto> attendanceList) {
        this.attendanceList = attendanceList;
    }
}

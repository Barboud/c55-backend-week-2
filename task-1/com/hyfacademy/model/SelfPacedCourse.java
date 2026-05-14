package com.hyfacademy.model;

import com.hyfacademy.service.Reportable;

public class SelfPacedCourse extends Course implements Reportable {
    public int estimatedHours;

    public SelfPacedCourse(String courseName, int maxStudents, int estimatedHours) {
        super(courseName, maxStudents);
        this.estimatedHours = estimatedHours;
    }

    @Override
    public String getCourseType() {
        return "Self-Paced";
    }

    @Override
    public String getScheduleInfo() {
        return "Estimated: "+ estimatedHours +" hours — complete at your own pace";
    }

    @Override
    public void generateReport() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("  COURSE REPORT — Self-Paced");
        System.out.println("══════════════════════════════════════════");
        System.out.println("  ID          : " + getCourseId());
        System.out.println("  Name        : " + getCourseName());
        System.out.println("  Capacity    : " + capacityStatus());
        System.out.println("  Est. Hours  : " + estimatedHours);

        printStudentsTable();
    }
}

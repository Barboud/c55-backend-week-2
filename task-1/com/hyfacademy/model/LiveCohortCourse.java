package com.hyfacademy.model;

import com.hyfacademy.service.Reportable;

public class LiveCohortCourse extends Course implements Reportable {
    public String startDate;
    public String endDate;
    public Mentor mentor;

    public LiveCohortCourse(String courseName, int maxStudents, String startDate, String endDate) {
        super(courseName, maxStudents);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getCourseType() {
        return "Live Cohort";
    }

    @Override
    public String getScheduleInfo() {
        String mentorName = (mentor != null) ? mentor.getName() : "Unassigned";
        return startDate + " to " + endDate + " | Mentor: " + mentorName;
    }

    public void assignMentor(Mentor mentor){
        this.mentor = mentor;
        mentor.assignToCourse(this);
    }

    @Override
    public void generateReport() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("  COURSE REPORT — Live Cohort");
        System.out.println("══════════════════════════════════════════");
        System.out.println("  ID          : " + getCourseId());
        System.out.println("  Name        : " + getCourseName());
        System.out.println("  Schedule    : " + startDate + " to " + endDate);

        System.out.println("  Mentor      : " + mentor.getName());

        System.out.println("  Capacity    : " + capacityStatus());

        printStudentsTable();

    }
}

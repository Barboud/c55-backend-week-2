package com.hyfacademy.model;

public class LiveCohortCourse extends Course {
    public String startDate;
    public String endDate;
    public Mentor mentor;

    LiveCohortCourse(String courseName, int maxStudents, String startDate, String endDate) {
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
        return startDate +" to "+ endDate +" | Mentor: " + mentor.getName();
    }

    public void assignMentor(Mentor mentor){
        this.mentor = mentor;
        mentor.assignToCourse(this);
    }
}

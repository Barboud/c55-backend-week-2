package com.hyfacademy.model;

public class SelfPacedCourse extends Course {
    public int estimatedHours;

    SelfPacedCourse(String courseName, int maxStudents, int estimatedHours) {
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
}

package com.hyfacademy.model;

import com.hyfacademy.exception.EnrolmentException;

public class Mentor extends User {

    private final String expertise;
    private Course[] assignedCourses = new Course[3];
    private int courseCount = 0;
    private static int totalMentors = 0;


    public Mentor(String name, String email, String expertise) {
        super(name, email, generateId());
        this.expertise = expertise;
        totalMentors++;
    }

    private static String generateId(){
        int id = totalMentors + 1;
        if (id >= 100) {
            return "COA-" + id;
        } else if (id >= 10) {
            return "COA-0" + id;
        } else {
            return "COA-00" + id;
        }
    }

    @Override
    public String getRole() {
        return "MENTOR";
    }

    public void assignToCourse(Course course) {
        // throws EnrolmentException if already at max (3 courses)
        if (courseCount >= 3) {
            throw new EnrolmentException("Mentor " + getName() + " has reached the maximum limit of 3 courses.");
        }
        assignedCourses[courseCount] = course;
        courseCount++;
    }

    public Course[] getAssignedCourses(){
        // returns assigned courses
        return assignedCourses;
    }

    public String getExpertise() {
        return expertise;
    }

    public int getCourseCount() {
        return courseCount;
    }
}

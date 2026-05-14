package com.hyfacademy.model;

import com.hyfacademy.exception.AlreadyEnrolledException;
import com.hyfacademy.exception.EnrolmentException;

public class Student extends User {

    private static int totalStudents;
    private Course[] enrolledCourses = new Course[5];
    private int courseCount;

    public Student(String name, String email) {
        super(name, email, generateId());
        totalStudents++;
    }

    private static String generateId(){
        int id = totalStudents + 1;
        if (id >= 100) {
            return "STU-" + id;
        } else if (id >= 10) {
            return "STU-0" + id;
        } else {
            return "STU-00" + id;
        }
    }

    @Override
    public String getRole() {
        return "STUDENT";
    }


    public void enrol(Course course) {
        // adds to enrolledCourses; throws AlreadyEnrolledException if already enrolled, EnrolmentException if the student's own course list is full (5 courses max)
        for (int i = 0; i < courseCount; i++) {
            if (enrolledCourses[i].getCourseId().equals(course.getCourseId())) {
                throw new AlreadyEnrolledException(this.getName(), course.getCourseName());
            }
        }

        if (courseCount >= 5) {
            throw new EnrolmentException("Student's enrolment limit reached (max: 5 courses)");
        }

        enrolledCourses[courseCount] = course;
        courseCount++;
    }

    public Course[] getCourses() {
        return enrolledCourses;
    }

    public int getCourseCount() {
        return courseCount;
    }


    public int getProgress(String courseName) {
        // returns the student's progress in that course (0–100); throws EnrolmentException if not enrolled
        for (int i = 0; i < courseCount; i++) {
            if (enrolledCourses[i].getCourseName().equals(courseName)) {

                return enrolledCourses[i].getStudentProgress(this);
            }
        }
        throw new EnrolmentException( getName() + " is not enrolled in course: " + courseName);
    }
}
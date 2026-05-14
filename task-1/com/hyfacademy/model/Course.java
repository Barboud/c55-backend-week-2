package com.hyfacademy.model;

import com.hyfacademy.exception.AlreadyEnrolledException;
import com.hyfacademy.exception.CourseFullException;
import com.hyfacademy.exception.EnrolmentException;
import com.hyfacademy.exception.InvalidProgressException;

public abstract class Course {

    private String courseName;
    private String courseId;
    private int maxStudents;
    private int enrolledCount;

    private String[] studentNames;
    private int[] studentProgress;
    private static int totalCourses;

    Course(String courseName, int maxStudents){
        this.courseName = courseName;
        this.maxStudents = maxStudents;

        this.studentNames = new String[maxStudents];
        this.studentProgress = new int[maxStudents];

        this.courseId = generateId();
        totalCourses++;
    }

    private static String generateId(){
        int id = totalCourses + 1;
        if (id >= 100) {
            return "CRS-" + id;
        } else if (id >= 10) {
            return "CRS-0" + id;
        } else {
            return "CRS-00" + id;
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public int getStudentProgress(Student student) {
        // returns progress (0–100); throws EnrolmentException if not enrolled
        for (int i = 0; i < enrolledCount; i++) {
            if (studentNames[i].equals(student.getName())) {
                return studentProgress[i];
            }
        }
        throw new EnrolmentException("Student not enrolled in this course.");
    }

    public void updateProgress(Student student, int progress) {
        // throws EnrolmentException if student not enrolled.
        if (progress < 0 || progress > 100) {
            throw new InvalidProgressException(progress);
        }
        for (int i = 0; i < enrolledCount; i++) {
            if (studentNames[i].equals(student.getName())) {
                studentProgress[i] = progress;
                return;
            }
        }
        throw new EnrolmentException("Student not enrolled in this course.");
    }

    public static int getTotalCourses() {
        return totalCourses;
    }

    public void enrol(Student student) {
        // throws CourseFullException if at capacity, AlreadyEnrolledException if already enrolled; increments enrolledCount
        if (enrolledCount >= maxStudents) {
            throw new CourseFullException(getCourseName(),maxStudents);
        }

        for (int i = 0; i < enrolledCount; i++) {
            if (studentNames[i].equals(student.getName())) {
                throw new AlreadyEnrolledException(student.getName(), courseName);
            }
        }

        studentNames[enrolledCount] = student.getName();
        studentProgress[enrolledCount] = 0;
        enrolledCount++;
    }

    public abstract String getCourseType();
    public abstract String getScheduleInfo();

    public boolean isFull() {
        return enrolledCount >= maxStudents;
    }

}

package com.hyfacademy.model;

import com.hyfacademy.exception.AlreadyEnrolledException;
import com.hyfacademy.exception.CourseFullException;
import com.hyfacademy.exception.EnrolmentException;
import com.hyfacademy.exception.InvalidProgressException;
import com.hyfacademy.service.Enrollable;

public abstract class Course implements Enrollable {

    private String courseName;
    private String courseId;
    private int maxStudents;
    private int enrolledCount;

    private Student[] enrolledStudents;
    private int[] studentProgress;
    private static int totalCourses;

    Course(String courseName, int maxStudents){
        this.courseName = courseName;
        this.maxStudents = maxStudents;

        this.enrolledStudents = new Student[maxStudents];
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

    @Override
    public int getMaxStudents() {
        return maxStudents;
    }

    @Override
    public int getEnrolledCount() {
        return enrolledCount;
    }

    public int getStudentProgress(Student student) {
        // returns progress (0–100); throws EnrolmentException if not enrolled
        for (int i = 0; i < enrolledCount; i++) {
            if (enrolledStudents[i].getName().equals(student.getName())) {
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
            if (enrolledStudents[i].getName().equals(student.getName())) {
                studentProgress[i] = progress;
                return;
            }
        }
        throw new EnrolmentException("Student not enrolled in this course.");
    }

    public static int getTotalCourses() {
        return totalCourses;
    }

    @Override
    public void enrol(Student student) {
        // throws CourseFullException if at capacity, AlreadyEnrolledException if already enrolled; increments enrolledCount
        if (enrolledCount >= maxStudents) {
            throw new CourseFullException(getCourseName(),maxStudents);
        }

        for (int i = 0; i < enrolledCount; i++) {
            if (enrolledStudents[i].getName().equals(student.getName())) {
                throw new AlreadyEnrolledException(student.getName(), courseName);
            }
        }

        enrolledStudents[enrolledCount] = student;
        studentProgress[enrolledCount] = 0;
        enrolledCount++;
    }

    public abstract String getCourseType();
    public abstract String getScheduleInfo();

    @Override
    public boolean isFull() {
        return enrolledCount >= maxStudents;
    }


    @Override
    public String toString() {
        // [CRS-001] Java Basics (Self-Paced) | Enrolled: 3/20
        return String.format("[%s] %s (%s) | Enrolled: %d/%d",
                courseId, courseName, getCourseType(), enrolledCount, maxStudents );
    }


    public void printStudentsTable() {
        System.out.println("──────────────────────────────────────────");
        System.out.println("  STUDENT PROGRESS");
        System.out.println("──────────────────────────────────────────");

        for (int i = 0; i < enrolledCount; i++) {
            Student student = enrolledStudents[i];
            int progress = studentProgress[i];

            long filled = Math.round(progress / 10.0);

            String bar = "";
            for (int j = 1; j <= 10; j++) {
                bar += (j <= filled) ? "█" : "░";
            }

            System.out.printf("  %-10s %-20s %3d%%   %s\n",
                    student.getUserId(), student.getName(), progress, bar);
        }

        int total = 0;
        for(int i=0; i<enrolledCount; i++) total += studentProgress[i];
        int avg = (enrolledCount > 0) ? total / enrolledCount : 0;

        System.out.println("──────────────────────────────────────────");
        System.out.println("  Avg Progress : " + avg + "%");
        System.out.println("══════════════════════════════════════════");
    }


}

package com.hyfacademy.model;

import com.hyfacademy.util.GradeUtils;

public class Student extends User{
    private int[] grades = new int[5];
    private static int totalStudents;
    private int[] enrolledCourses = new int[5];
    private int courseCount;

    public Student(String name, String email) {
        super(name, email, generateId());
        this.grades = new int[]{0, 0, 0, 0, 0};
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
        return "";
    }

    public int[] getGrades() {
        return grades;
    }

    public boolean setGrades(int moduleIndex, int grade) {

        if (moduleIndex > 4) {
            System.out.println("moduleIndex must be between 0–4");
            return false;
        } else if (grade > 100 || grade < 0) {
            System.out.println("Grade must be between 0–100");
            return false;
        }
        this.grades[moduleIndex] = grade;
        return true;
    }

    @Override
    public String toString(){

        double average = GradeUtils.calculateAverage(this.grades);
        String pass = "PASS";
        if (!GradeUtils.isPassing(average)) {
            pass = "FAIL";
        }
        String name = getName();
        String studentId = getUserId();
        return "[" + studentId + "] " + name + " — Avg: " + average + " — " + pass;
    }

}
package model;

import util.GradeUtils;

public class Student {
    private String name;
    private String studentId;
    private int[] grades = new int[5];
    private static int totalStudents;

    public Student(String name) {
        this.name = name;
        this.studentId = generateStudentId();
        this.grades = new int[]{0, 0, 0, 0, 0};
        totalStudents++;
    }

    private String generateStudentId(){
        int id = totalStudents + 1;
        if (id >= 100) {
            return "HYF-" + id;
        } else if (id >= 10) {
            return "HYF-0" + id;
        } else {
            return "HYF-00" + id;
        }
    }

    public String getStudentId(){
        return this.studentId;
    }

    public String getName(){
        return this.name;
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


    public String toString(){

        double average = GradeUtils.calculateAverage(this.grades);
        String pass = "PASS";
        if (!GradeUtils.isPassing(average)) {
            pass = "FAIL";
        }
        String name = getName();
        String studentId = getStudentId();
        return "[" + studentId + "] " + name + " — Avg: " + average + " — " + pass;
    }

}
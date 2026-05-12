package com.hyfacademy.util;

public class GradeUtils {
    public static final int MODULE_PASS_MARK = 55;
    public static final double TRACK_PASS_AVERAGE = 60.0;
    public static final int MODULE_COUNT = 5;
    public static final String[] MODULE_NAMES = {"Java Basics", "Control Flow", "OOP Fundamentals", "Arrays & Collections", "Input & Output"};

    // to prevent instantiation.
    private GradeUtils(){}

    public static double calculateAverage(int[] grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }

        return (double) sum / grades.length;
    }

    public static boolean isPassing(double average){
        return average >= TRACK_PASS_AVERAGE;
    }

    public static boolean isModulePassing(int grade) {
        return grade >= MODULE_PASS_MARK;
    }

    public static char getLetterGrade(double average){
        if (average >= 90) {
            return 'A';
        } else if (average >= 80) {
            return 'B';
        } else if (average >= 70) {
            return 'C';
        } else if (average >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static String formatGrade(int grade) {
        if (grade == 100){
            return "100";
        } else if (grade >= 10) {
            return " " + grade;
        } else {
            return "  " + grade;
        }
    }


}
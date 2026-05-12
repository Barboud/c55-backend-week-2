package com.hyfacademy;
import com.hyfacademy.exception.CourseFullException;
import com.hyfacademy.exception.EnrolmentException;
import com.hyfacademy.service.GradeService;

public class Main {
    public static void main(String[] args) {
        //GradeService service = new GradeService();
        //service.run();

        int currentCount = 19;
        String courseName = "Java";
        int max = 20;
        if (currentCount >= max) {
            throw new CourseFullException(courseName, max);
        }
        System.out.println("Done!");

    }
}
package com.hyfacademy.service;

import com.hyfacademy.model.*;
import com.hyfacademy.exception.*;
import java.util.Scanner;

public class PlatformService {
    private Course[] courses = new Course[10];
    private int courseCount = 0;
    private Student[] students = new Student[20];
    private int studentCount = 0;
    private Mentor[] mentors = new Mentor[5];
    private int mentorCount = 0;

    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║       HYF ACADEMY COURSE PLATFORM        ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.println("  1. Add course");
            System.out.println("  2. Add student");
            System.out.println("  3. Add mentor");
            System.out.println("  4. Enrol student in course");
            System.out.println("  5. Update student progress");
            System.out.println("  6. View all courses");
            System.out.println("  7. View course report");
            System.out.println("  8. View all students");
            System.out.println("  9. Exit");
            System.out.println("══════════════════════════════════════════");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addCourse();
                case "2" -> addStudent();
                case "3" -> addMentor();
                case "4" -> enrolStudent();
                case "5" -> updateProgress();
                case "6" -> viewAllCourses();
                case "7" -> viewCourseReport();
                case "8" -> viewAllStudents();
                case "9" -> {
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void addCourse() {
        if (courseCount >= courses.length) {
            System.out.println("Error: Maximum course capacity reached.");
            return;
        }
        System.out.print("Course type (1: Self-Paced, 2: Live Cohort): ");
        String type = scanner.nextLine();
        System.out.print("Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine());

        if (type.equals("1")) {
            System.out.print("Estimated Hours: ");
            int hours = Integer.parseInt(scanner.nextLine());
            courses[courseCount++] = new SelfPacedCourse(name, capacity, hours);
        } else {
            System.out.print("Start Date (YYYY-MM-DD): ");
            String start = scanner.nextLine();
            System.out.print("End Date (YYYY-MM-DD): ");
            String end = scanner.nextLine();
            courses[courseCount++] = new LiveCohortCourse(name, capacity, start, end);
        }
        System.out.println("Course added successfully.");
    }

    private void addStudent() {
        if (studentCount >= students.length) {
            System.out.println("Error: Maximum student capacity reached.");
            return;
        }
        System.out.print("Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Student Email: ");
        String email = scanner.nextLine();
        students[studentCount++] = new Student(name, email);
        System.out.println("Student registered successfully.");
    }

    private void addMentor() {
        if (mentorCount >= mentors.length) {
            System.out.println("Error: Maximum mentor capacity reached.");
            return;
        }
        System.out.print("Mentor Name: ");
        String name = scanner.nextLine();
        System.out.print("Mentor Email: ");
        String email = scanner.nextLine();
        System.out.print("Expertise: ");
        String expertise = scanner.nextLine();
        mentors[mentorCount++] = new Mentor(name, email, expertise);
        System.out.println("Mentor added successfully.");
    }

    private void enrolStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("Enter Course ID: ");
            String courseId = scanner.nextLine();

            Student student = findStudent(studentId);
            Course course = findCourse(courseId);

            if (student != null && course != null) {
                course.enrol(student);
                System.out.println("Enrolment successful.");
            } else {
                System.out.println("Error: Student or Course not found.");
            }
        } catch (EnrolmentException e) {
            System.out.println("Enrolment Error: " + e.getMessage());
        }
    }

    private void updateProgress() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("Enter Course ID: ");
            String courseId = scanner.nextLine();
            System.out.print("Progress (0-100): ");
            int p = Integer.parseInt(scanner.nextLine());

            Student student = findStudent(studentId);
            Course course = findCourse(courseId);

            if (student != null && course != null) {
                course.updateProgress(student, p);
                System.out.println("Progress updated successfully.");
            } else {
                System.out.println("Error: Student or Course not found.");
            }
        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    private void viewAllCourses() {
        if (courseCount == 0) {
            System.out.println("No courses available.");
            return;
        }
        for (int i = 0; i < courseCount; i++) {
            System.out.println(courses[i].toString());
        }
    }

    private void viewCourseReport() {
        System.out.print("Enter Course ID: ");
        String courseId = scanner.nextLine();
        Course course = findCourse(courseId);

        if (course == null) {
            System.out.println("Error: Course not found.");
            return;
        }

        ((Reportable) course).generateReport();
    }

    private void viewAllStudents() {
        if (studentCount == 0) {
            System.out.println("No students registered.");
            return;
        }
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i].getUserId() + " - " + students[i].getName());
        }
    }

    private Student findStudent(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getUserId().equalsIgnoreCase(id)) return students[i];
        }
        return null;
    }

    private Course findCourse(String id) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseId().equalsIgnoreCase(id)) return courses[i];
        }
        return null;
    }

}
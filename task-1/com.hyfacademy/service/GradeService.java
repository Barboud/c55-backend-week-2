package service;

import model.Student;
import util.GradeUtils;
import java.util.Scanner;

public class GradeService {
    private Student[] students = new Student[20];
    private int studentCount = 0;
    private Scanner scanner = new Scanner(System.in);

    public static final int MAX_STUDENTS = 20;

    public GradeService() {seedData();}

    public void addStudent(){
        if (studentCount == MAX_STUDENTS) {
            System.out.println("Capacity reached.");
        } else {
            System.out.print("Enter the student name: ");

            while (scanner.hasNextInt()) {
                System.out.print("Enter a valid student name: ");
                scanner.next();
            }

            String name = scanner.nextLine().trim();
            students[studentCount] = new Student(name);
            System.out.printf("Student %s with Id %s has been added.%n",students[studentCount].getName(), students[studentCount].getStudentId());
            System.out.println("══════════════════════════════════════");
            studentCount++;
        }
    }

    public void enterGrades(){

        System.out.print("Enter the student Id HYF-XXX: ");
        String id = "HYF-" + scanner.nextLine();
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("User not found!");
            return;
        }
        for (int i = 0; i < GradeUtils.MODULE_NAMES.length; i++) {
            boolean isGradeSet = false;
            while (!isGradeSet){
                System.out.printf("Enter grade for %s: ", GradeUtils.MODULE_NAMES[i]);
                while (!scanner.hasNextInt()) {
                    System.out.printf("Enter a valid grade for %s: ", GradeUtils.MODULE_NAMES[i]);
                    scanner.next();
                }
                int inputGrade = scanner.nextInt();
                scanner.nextLine();
                if (student.setGrades(i, inputGrade)) {
                    isGradeSet= true;
                }
            }

        }
        System.out.printf("Grades for %s with Id %s have been added.%n",student.getName(), student.getStudentId());
        System.out.println("══════════════════════════════════════");
    }


    public void viewAllStudents(){

        if (studentCount == 0) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("══════════════════════════════════════════════════════════════");
        System.out.printf("  %-10s %-20s %-10s %-7s %-10s%n", "ID", "NAME", "AVERAGE", "GRADE", "STATUS");
        System.out.println("══════════════════════════════════════════════════════════════");

        int passingCount = 0;

        for (int i = 0; i < studentCount; i++) {
            Student student = students[i];

            double average = GradeUtils.calculateAverage(student.getGrades());
            char letterGrade = GradeUtils.getLetterGrade(average);
            boolean passing = GradeUtils.isPassing(average);
            String status = passing ? "PASS" : "FAIL";

            if (passing) passingCount++;

            System.out.printf("  %-10s %-20s %-10.2f %-7c %-10s%n",
                    student.getStudentId(),
                    student.getName(),
                    average,
                    letterGrade,
                    status);
        }

        int failingCount = studentCount - passingCount;
        System.out.println("══════════════════════════════════════════════════════════════");
        System.out.printf("  Total students: %d   Passing: %d   Failing: %d%n",
                studentCount, passingCount, failingCount);
        System.out.println("══════════════════════════════════════════════════════════════");
    }

    public void viewStudentReport(){

        System.out.print("Enter the student Id HYF-XXX: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid student Id HYF-XXX: ");
            scanner.next();
        }
        String id = "HYF-" + scanner.nextLine();

        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Error: Student with ID " + id + " not found.");
            return;
        }

        int[] grades = student.getGrades();
        double average = GradeUtils.calculateAverage(grades);
        char letterGrade = GradeUtils.getLetterGrade(average);
        boolean isTotalPassing = GradeUtils.isPassing(average);

        System.out.println("══════════════════════════════════════");
        System.out.println("  STUDENT REPORT");
        System.out.println("══════════════════════════════════════");
        System.out.printf("  ID      : %s%n", student.getStudentId());
        System.out.printf("  Name    : %s%n", student.getName());
        System.out.println("──────────────────────────────────────");
        System.out.println("  MODULE GRADES");
        System.out.println("──────────────────────────────────────");

        for (int i = 0; i < GradeUtils.MODULE_NAMES.length; i++) {
            int grade = grades[i];
            String moduleStatus = GradeUtils.isModulePassing(grade) ? "PASS" : "FAIL";

            System.out.printf("  %-22s:  %s   %s%n",
                    GradeUtils.MODULE_NAMES[i],
                    GradeUtils.formatGrade(grade),
                    moduleStatus);
        }

        System.out.println("──────────────────────────────────────");
        System.out.printf("  Average  :  %.2f%n", average);
        System.out.printf("  Grade    :  %c%n", letterGrade);
        System.out.printf("  Status   :  %s %n",
                isTotalPassing ? "✓ PASS" : "✗ FAIL");
        System.out.println("══════════════════════════════════════");
    }

    private Student findStudentById(String id) {
        for (int i = 0; i < studentCount; i++) { // only loop deepens in studentCount not entire array
            if (students[i].getStudentId().equals(id)) {
                return students[i];
            }
        }
        return null;
    }




    public void run(){
        boolean run = true;
        while (run) {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║         HYF ACADEMY - GRADE MGR      ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("  1. Add student");
            System.out.println("  2. Enter grades");
            System.out.println("  3. View all students");
            System.out.println("  4. View student report");
            System.out.println("  5. Exit");
            System.out.println("══════════════════════════════════════");
            System.out.print("Choose an option: ");


            while (!scanner.hasNextInt()) {
                System.out.print("Type a valid number: ");
                scanner.next();
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    enterGrades();
                    break;
                case 3:
                    viewAllStudents();
                    break;
                case 4:
                    viewStudentReport();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }

    }

    // I use AI to generate this method to test viewStudentReport(),viewAllStudents()
    private void seedData() {
        String[] names = {"Alice van der Berg", "Bob Jansen", "Carol de Groot", "Dave Miller"};
        int[][] mockGrades = {
                {88, 76, 91, 70, 87},
                {50, 45, 60, 55, 52},
                {95, 92, 100, 88, 90},
                {60, 65, 70, 58, 62}
        };

        for (int i = 0; i < names.length; i++) {
            students[studentCount] = new Student(names[i]);

            for (int j = 0; j < mockGrades[i].length; j++) {
                students[studentCount].setGrades(j, mockGrades[i][j]);
            }

            studentCount++;
        }
    }


}
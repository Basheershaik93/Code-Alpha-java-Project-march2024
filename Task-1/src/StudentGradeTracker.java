import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    private static final int MAX_STUDENTS = 50;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();

        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                      Student Grade Tracker                        ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");

        System.out.print("\nEnter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (numStudents > MAX_STUDENTS) {
            System.out.println("\n⚠️ Error: Maximum number of students allowed is " + MAX_STUDENTS);
            return;
        }

        System.out.println("\nEnter grades for each student :");
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Student " + (i + 1) + ": ");
            String gradeInput = scanner.nextLine();

            if (!gradeInput.isEmpty()) {
                try {
                    int grade = Integer.parseInt(gradeInput);
                    grades.add(grade);
                } catch (NumberFormatException ex) {
                    System.out.println("\n⚠️ Error: Invalid grade for Student " + (i + 1));
                    return;
                }
            } else {
                System.out.println("\n⚠️ Error: Grade cannot be empty for Student " + (i + 1));
                return;
            }
        }

        calculateAndDisplayResults(grades);
    }

    private static void calculateAndDisplayResults(ArrayList<Integer> grades) {
        int sum = 0;
        int highestGrade = grades.get(0);
        int lowestGrade = grades.get(0);

        for (int grade : grades) {
            sum += grade;
            highestGrade = Math.max(highestGrade, grade);
            lowestGrade = Math.min(lowestGrade, grade);
        }

        double averageGrade = (double) sum / grades.size();

        System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         Results                                  ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝");

        System.out.println("\n📊 Average grade: " + averageGrade);
        System.out.println("🏆 Highest grade: " + highestGrade);
        System.out.println("🔽 Lowest grade: " + lowestGrade);

        
    }
}
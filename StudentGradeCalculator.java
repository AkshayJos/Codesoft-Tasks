import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\t\tWelcome to Student Grade calculator !!");
        System.out.println("\n\t\tPlease fill the following information.");
        System.out.print("\n  Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        System.out.println();

        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];
        }

        double averagePercentage = (double) totalMarks / numSubjects;

        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }
      
        System.out.print("\n   -------------------------------------------------------");
        System.out.print("\n   |  ");
        System.out.print("  Total Marks " + "  |  ");
        System.out.print("Average Percentage " + "  |  ");
        System.out.print("Grade " + "  |  ");
        System.out.print("\n   |");
        System.out.print("       " + totalMarks + "        |");
        System.out.print("         " + averagePercentage + "%"+ "         |");
        System.out.print("    " + grade+ "     |");
        System.out.println("\n   -------------------------------------------------------");

        scanner.close();
    }
}
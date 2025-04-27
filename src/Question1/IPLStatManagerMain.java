package Question1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IPLStatManagerMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PerformanceManager performanceManager = new PerformanceManager();
        int choice;

        do {
            System.out.println("\nIPL Player Statistics Manager");
            System.out.println("1. Add New Match Performance");
            System.out.println("2. Display All Performances for a Specific Player");
            System.out.println("3. Display All Performances with a Specific Rating");
           // System.out.println("4. Calculate Average Runs and Wickets for a Player");
            System.out.println("5. Display All Performances Sorted by Match Date");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        performanceManager.addPerformance(scanner);
                        break;
                    case 2:
                        performanceManager.displayPerformancesByPlayer(scanner);
                        break;
                    case 3:
                        performanceManager.displayPerformancesByRating(scanner);
                        break;
                    case 5:
                        performanceManager.displayAllPerformancesSortedByDate();
                        break;
                    case 0:
                        System.out.println("Exiting the application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Invalid input. Please enter a number.");
                scanner.nextLine(); 
                choice = -1; 
            }

        } while (choice != 0);

        scanner.close();
    }
}
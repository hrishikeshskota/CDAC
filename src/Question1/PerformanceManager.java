package Question1;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class PerformanceManager {
    private List<PlayerPerformance> performances = new ArrayList<>();

    public void addPerformance(Scanner scanner) {
        try {
            System.out.println("Enter player name:");
            String playerName = scanner.nextLine().trim();
            if (playerName.isEmpty()) {
                throw new InputMismatchException("Player name cannot be empty.");
            }

            System.out.println("Enter team name:");
            String teamName = scanner.nextLine().trim();
            if (teamName.isEmpty()) {
                throw new InputMismatchException("Team name cannot be empty.");
            }

            System.out.println("Enter match date (yyyy-MM-dd):");
            String dateString = scanner.nextLine().trim();
            LocalDate matchDate;
            try {
                matchDate = LocalDate.parse(dateString, PlayerPerformance.getDateFormatter());
            } catch (DateTimeParseException e) {
                throw new InputMismatchException("Invalid date format. Please use yyyy-MM-dd.");
            }

            System.out.println("Enter runs scored:");
            int runsScored = scanner.nextInt();
            if (runsScored < 0) {
                throw new InputMismatchException("Runs scored cannot be negative.");
            }

            System.out.println("Enter wickets taken:");
            int wicketsTaken = scanner.nextInt();
            if (wicketsTaken < 0) {
                throw new InputMismatchException("Wickets taken cannot be negative.");
            }
            scanner.nextLine(); // Consume newline

            System.out.println("Enter performance rating (EXCELLENT, GOOD, AVERAGE, POOR):");
            String ratingString = scanner.nextLine().trim().toUpperCase();
            PerformanceRating performanceRating;
            try {
                performanceRating = PerformanceRating.valueOf(ratingString);
            } catch (IllegalArgumentException e) {
                throw new InputMismatchException("Invalid performance rating.");
            }

            performances.add(new PlayerPerformance(playerName, teamName, matchDate, runsScored, wicketsTaken, performanceRating));
            System.out.println("Performance added successfully.");

        } catch (InputMismatchException e) {
            System.err.println("Error: " + e.getMessage());
            if (scanner.hasNext()) {
                scanner.next(); 
            }
        }
    }

    public void displayPerformancesByPlayer(Scanner scanner) {
        System.out.println("Enter player name to search:");
        String playerName = scanner.nextLine().trim();
        if (playerName.isEmpty()) {
            System.out.println("Player name cannot be empty.");
            return;
        }

        List<PlayerPerformance> playerPerformances = performances.stream()
                .filter(performance -> performance.getPlayerName().equalsIgnoreCase(playerName))
                .collect(Collectors.toList());

        if (playerPerformances.isEmpty()) {
            System.out.println("No performances found for player: " + playerName);
        } else {
            System.out.println("\nPerformances for " + playerName + ":");
            playerPerformances.forEach(System.out::println);
        }
    }

    public void displayPerformancesByRating(Scanner scanner) {
        System.out.println("Enter performance rating to search (EXCELLENT, GOOD, AVERAGE, POOR):");
        String ratingString = scanner.nextLine().trim().toUpperCase();
        try {
            PerformanceRating rating = PerformanceRating.valueOf(ratingString);
            List<PlayerPerformance> ratedPerformances = performances.stream()
                    .filter(performance -> performance.getPerformanceRating() == rating)
                    .collect(Collectors.toList());

            if (ratedPerformances.isEmpty()) {
                System.out.println("No performances found with rating: " + rating);
            } else {
                System.out.println("\nPerformances with rating " + rating + ":");
                ratedPerformances.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: Invalid performance rating.");
        }
    }

//    public void calculateAverageStats(Scanner scanner) {
//        System.out.println("Enter player name to calculate average stats:");
//        String playerName = scanner.nextLine().trim();
//        if (playerName.isEmpty()) {
//            System.out.println("Player name cannot be empty.");
//            return;
//        }
//
//        List<PlayerPerformance> playerPerformances = performances.stream()
//                .filter(performance -> performance.getPlayerName().equalsIgnoreCase(playerName))
//                .collect(Collectors.toList());
//
//        if (playerPerformances.isEmpty()) {
//            System.out.println("No performances found for player: " + playerName);
//            return;
//        }
//


    public void displayAllPerformancesSortedByDate() {
        if (performances.isEmpty()) {
            System.out.println("No performances recorded yet.");
            return;
        }

        List<PlayerPerformance> sortedPerformances = performances.stream()
                .sorted(Comparator.comparing(PlayerPerformance::getMatchDate))
                .collect(Collectors.toList());

        System.out.println("\nAll performances sorted by match date:");
        sortedPerformances.forEach(System.out::println);
    }
}
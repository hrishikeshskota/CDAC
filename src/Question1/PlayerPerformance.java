package Question1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PlayerPerformance {
    private int playerId;
    private String playerName;
    private String teamName;
    private LocalDate matchDate;
    private int runsScored;
    private int wicketsTaken;
    private PerformanceRating performanceRating;
    private static int nextPlayerId = 1;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public PlayerPerformance(String playerName, String teamName, LocalDate matchDate, int runsScored, int wicketsTaken, PerformanceRating performanceRating) {
        this.playerId = nextPlayerId++;
        this.playerName = playerName;
        this.teamName = teamName;
        this.matchDate = matchDate;
        this.runsScored = runsScored;
        this.wicketsTaken = wicketsTaken;
        this.performanceRating = performanceRating;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public PerformanceRating getPerformanceRating() {
        return performanceRating;
    }

    public static DateTimeFormatter getDateFormatter() {
        return DATE_FORMATTER;
    }

    @Override
    public String toString() {
        return "Player ID: " + playerId +
               ", Name: " + playerName +
               ", Team: " + teamName +
               ", Date: " + matchDate.format(DATE_FORMATTER) +
               ", Runs: " + runsScored +
               ", Wickets: " + wicketsTaken +
               ", Rating: " + performanceRating;
    }
}
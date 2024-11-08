package entity;

import java.util.List;

public class LeagueData {
    private int ranking;
    private double points;
    private List<String> words;

    public LeagueData(int ranking, double points, List<String> words) {
        this.ranking = ranking;
        this.points = points;
        this.words = words;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public double getPoints() {
        return points;
    }
    public void setPoints(double points) {
        this.points = points;
    }
    public List<String> getWords() {
        return words;
    }
    public void setWords(List<String> words) {
        this.words = words;
    }
    public void addPoints(double points) {
        this.points += points;
    }
}

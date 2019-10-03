package main;

public class Player
{
    private String name;
    private int goal;
    private int score;
    private int turnScore;
    
    public Player(final String name, final int goal) {
        this.name = name;
        this.goal = goal;
        this.score = 0;
        this.turnScore = 0;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void setScore(final int n0) {
        this.score = n0;
    }
    
    public int getGoal() {
        return this.goal;
    }
    
    public int getTurnScore() {
        return this.turnScore;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void changeTurnScore(final int n) {
        this.turnScore = n;
    }
    
    public void incrementTurnScore(final int n) {
        this.turnScore += n;
    }
    
    public void endTurn() {
        this.score += this.turnScore;
    }
}

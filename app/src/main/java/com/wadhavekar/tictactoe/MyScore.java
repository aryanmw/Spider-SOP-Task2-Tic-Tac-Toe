package com.wadhavekar.tictactoe;

public class MyScore {
    String name;
    int score;

    public MyScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getPlayerName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

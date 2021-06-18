package com.myapplicationdev.android.fyp.Model;

import java.io.Serializable;

public class Leaderboard implements Serializable {

    private int id;
    private String mode;
    private String score;
    private String date;

    public Leaderboard(int id, String mode, String score, String date) {
        this.id = id;
        this.mode = mode;
        this.score = score;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package com.myapplicationdev.android.fyp.Models;

import java.io.Serializable;

public class ScoreBoard implements Serializable {

    private int id;
    private String username;
    private String score;
    private String mode;
    private String date;

    public ScoreBoard(int id, String username, String score, String mode, String date) {
        this.id = id;
        this.username = username;
        this.score = score;
        this.mode = mode;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

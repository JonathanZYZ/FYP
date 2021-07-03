package com.myapplicationdev.android.fyp.Model;

import java.io.Serializable;

public class ScoreBoard implements Serializable {

    private int id;
    private String username;
    private String score;
    private String date;

    public ScoreBoard(int id, String username, String score, String date) {
        this.id = id;
        this.username = username;
        this.score = score;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

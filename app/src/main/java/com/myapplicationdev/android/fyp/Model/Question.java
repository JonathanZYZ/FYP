package com.myapplicationdev.android.fyp.Model;

import java.io.Serializable;
import java.sql.Blob;

public class Question implements Serializable {
    private String mode;
    private String questionsNum;
    private Blob questionsImg;
    private Blob theMCQOption1Reaction;
    private Blob theMCQOption2Reaction;


    public Question(String mode, String questionsNum, Blob questionsImg, Blob option1, Blob theMCQOption1Reaction, Blob theMCQOption2Reaction) {
        this.mode = mode;
        this.questionsNum = questionsNum;
        this.questionsImg = questionsImg;
        this.theMCQOption1Reaction = theMCQOption1Reaction;
        this.theMCQOption2Reaction = theMCQOption2Reaction;

    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getQuestionsNum() {
        return questionsNum;
    }

    public void setQuestionsNum(String questionsNum) {
        this.questionsNum = questionsNum;
    }

    public Blob getQuestionsImg() {
        return questionsImg;
    }

    public void setQuestionsImg(Blob questionsImg) {
        this.questionsImg = questionsImg;
    }

    public Blob gettheMCQOption1Reaction() {
        return theMCQOption1Reaction;
    }

    public void settheMCQOption1Reaction(Blob theMCQOption1Reaction) {
        this.theMCQOption1Reaction = theMCQOption1Reaction;
    }

    public Blob gettheMCQOption2Reaction() {
        return theMCQOption2Reaction;
    }

    public void settheMCQOption2Reaction(Blob theMCQOption2Reaction) {
        this.theMCQOption2Reaction = theMCQOption2Reaction;
    }
}
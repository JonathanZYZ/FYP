package com.myapplicationdev.android.fyp;

import java.sql.Blob;

public class Questions_POJO {
    private String mode;
    private String questionsNum;
    private Blob questionsImg;
    private Blob MCQoption1Reaction;
    private Blob MCQoption2Reaction;


    public Questions_POJO(String mode, String questionsNum, Blob questionsImg, Blob option1, Blob MCQoption1Reaction, Blob MCQoption2Reaction) {
        this.mode = mode;
        this.questionsNum = questionsNum;
        this.questionsImg = questionsImg;
        this.MCQoption1Reaction = MCQoption1Reaction;
        this.MCQoption2Reaction = MCQoption2Reaction;

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

    public Blob getMCQoption1Reaction() {
        return MCQoption1Reaction;
    }

    public void setMCQoption1Reaction(Blob MCQoption1Reaction) {
        this.MCQoption1Reaction = MCQoption1Reaction;
    }

    public Blob getMCQoption2Reaction() {
        return MCQoption2Reaction;
    }

    public void setMCQoption2Reaction(Blob MCQoption2Reaction) {
        this.MCQoption2Reaction = MCQoption2Reaction;
    }
}

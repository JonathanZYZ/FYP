package com.myapplicationdev.android.fyp.Model;

import java.io.Serializable;


public class QuestionEasy implements Serializable {
    private String mode;
    private String questionsNum;
    private int questionsImg;
    private int MCQoption1Reaction;
    private int MCQoption2Reaction;
    private int answerNum;


    public QuestionEasy(String mode, String questionsNum, int questionsImg, int MCQoption1Reaction, int MCQoption2Reaction, int answerNum) {
        this.mode = mode;
        this.questionsNum = questionsNum;
        this.questionsImg = questionsImg;
        this.MCQoption1Reaction = MCQoption1Reaction;
        this.MCQoption2Reaction = MCQoption2Reaction;
        this.answerNum = answerNum;
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

    public int getQuestionsImg() {
        return questionsImg;
    }

    public void setQuestionsImg(int questionsImg) {
        this.questionsImg = questionsImg;
    }

    public int getMCQoption1Reaction() {
        return MCQoption1Reaction;
    }

    public void setMCQoption1Reaction(int MCQoption1Reaction) {
        this.MCQoption1Reaction = MCQoption1Reaction;
    }

    public int getMCQoption2Reaction() {
        return MCQoption2Reaction;
    }

    public void setMCQoption2Reaction(int MCQoption2Reaction) {
        this.MCQoption2Reaction = MCQoption2Reaction;
    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }
}

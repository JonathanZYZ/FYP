package com.myapplicationdev.android.fyp.Model;

import java.io.Serializable;


public class QuestionEasy implements Serializable {
    private String mode;
    private String questionsNum;
    private int questionsBasic;
    private int option1Reaction;
    private int option2Reaction;
    private int answerNum;


    public QuestionEasy(String mode, String questionsNum, int questionsImg, int MCQoption1Reaction, int MCQoption2Reaction, int answerNum) {
        this.mode = mode;
        this.questionsNum = questionsNum;
        this.questionsBasic = questionsImg;
        this.option1Reaction = MCQoption1Reaction;
        this.option2Reaction = MCQoption2Reaction;
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

    public int getQuestionsBasic() {
        return questionsBasic;
    }

    public void setQuestionsBasic(int questionsBasic) {
        this.questionsBasic = questionsBasic;
    }

    public int getOption1Reaction() {
        return option1Reaction;
    }

    public void setOption1Reaction(int option1Reaction) {
        this.option1Reaction = option1Reaction;
    }

    public int getOption2Reaction() {
        return option2Reaction;
    }

    public void setOption2Reaction(int option2Reaction) {
        this.option2Reaction = option2Reaction;
    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }
}

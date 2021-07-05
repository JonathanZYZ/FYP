package com.myapplicationdev.android.fyp.Model;

import java.io.Serializable;

public class QuestionAdvanced implements Serializable {

    private final String mode;
    private final String qnNum;
    private final String didYouKnowMsg;
    private final int qnMainImage;
    private final int qnCount;
    private final int qn1Image;
    private final int numOfQuestions1;
    private final int ans1Image1;
    private final int ans1Image2;
    private final int ans1Image3;
    private final int ans1Image4;
    private final int correctNum1;
    private final int qn2Image;
    private final int numOfQuestions2;
    private final int ans2Image1;
    private final int ans2Image2;
    private final int ans2Image3;
    private final int ans2Image4;
    private final int correctNum2;
    private final int zoomedImage1;
    private final int zoomedImage2;
    private final int zoomedImage3;

    public QuestionAdvanced(String mode, String qnNum, String didYouKnowMsg, int qnMainImage, int qnCount, int qn1Image, int numOfQuestions1, int ans1Image1, int ans1Image2, int ans1Image3, int ans1Image4, int correctNum1, int qn2Image, int numOfQuestions2, int ans2Image1, int ans2Image2, int ans2Image3, int ans2Image4, int correctNum2, int zoomedImage1, int zoomedImage2, int zoomedImage3) {
        this.mode = mode;
        this.qnNum = qnNum;
        this.didYouKnowMsg = didYouKnowMsg;
        this.qnMainImage = qnMainImage;
        this.qnCount = qnCount;
        this.qn1Image = qn1Image;
        this.numOfQuestions1 = numOfQuestions1;
        this.ans1Image1 = ans1Image1;
        this.ans1Image2 = ans1Image2;
        this.ans1Image3 = ans1Image3;
        this.ans1Image4 = ans1Image4;
        this.correctNum1 = correctNum1;
        this.qn2Image = qn2Image;
        this.numOfQuestions2 = numOfQuestions2;
        this.ans2Image1 = ans2Image1;
        this.ans2Image2 = ans2Image2;
        this.ans2Image3 = ans2Image3;
        this.ans2Image4 = ans2Image4;
        this.correctNum2 = correctNum2;
        this.zoomedImage1 = zoomedImage1;
        this.zoomedImage2 = zoomedImage2;
        this.zoomedImage3 = zoomedImage3;
    }

    public String getMode() {
        return mode;
    }

    public String getQnNum() {
        return qnNum;
    }

    public String getDidYouKnowMsg() {
        return didYouKnowMsg;
    }

    public int getQnMainImage() {
        return qnMainImage;
    }

    public int getQnCount() {
        return qnCount;
    }

    public int getQn1Image() {
        return qn1Image;
    }

    public int getNumOfQuestions1() {
        return numOfQuestions1;
    }

    public int getAns1Image1() {
        return ans1Image1;
    }

    public int getAns1Image2() {
        return ans1Image2;
    }

    public int getAns1Image3() {
        return ans1Image3;
    }

    public int getAns1Image4() {
        return ans1Image4;
    }

    public int getCorrectNum1() {
        return correctNum1;
    }

    public int getQn2Image() {
        return qn2Image;
    }

    public int getNumOfQuestions2() {
        return numOfQuestions2;
    }

    public int getAns2Image1() {
        return ans2Image1;
    }

    public int getAns2Image2() {
        return ans2Image2;
    }

    public int getAns2Image3() {
        return ans2Image3;
    }

    public int getAns2Image4() {
        return ans2Image4;
    }

    public int getCorrectNum2() {
        return correctNum2;
    }

    public int getZoomedImage1() {
        return zoomedImage1;
    }

    public int getZoomedImage2() {
        return zoomedImage2;
    }

    public int getZoomedImage3() {
        return zoomedImage3;
    }
}

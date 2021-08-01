package com.myapplicationdev.android.fyp.Models;

import java.io.Serializable;

public class QuestionIntermediate implements Serializable {

    private final String mode;
    private final String qnNum;
    private final int qnMainImage;
    private final int qnCount;
    private final int qn1Image;
    private final int ans1Image1;
    private final int ans1Image2;
    private final int correctNum1;
    private final int solution1;
    private final int qn2Image;
    private final int ans2Image1;
    private final int ans2Image2;
    private final int ans2Image3;
    private final int ans2Image4;
    private final int correctNum2;
    private final int solution2;
    private final int qn3Image;
    private final int ans3Image1;
    private final int ans3Image2;
    private final int ans3Image3;
    private final int ans3Image4;
    private final int correctNum3;
    private final int solution3;

    public QuestionIntermediate(String mode, String qnNum, int qnMainImage, int qnCount, int qn1Image, int ans1Image1, int ans1Image2, int correctNum1, int qn2Image, int ans2Image1, int ans2Image2, int ans2Image3,int ans2Image4, int correctNum2, int qn3Image, int ans3Image1, int ans3Image2, int ans3Image3, int ans3Image4, int correctNum3,int solution1,int solution2,int solution3) {
        this.mode = mode;
        this.qnNum = qnNum;
        this.qnMainImage = qnMainImage;
        this.qnCount = qnCount;
        this.qn1Image = qn1Image;
        this.ans1Image1 = ans1Image1;
        this.ans1Image2 = ans1Image2;
        this.correctNum1 = correctNum1;
        this.solution1 = solution1;
        this.qn2Image = qn2Image;
        this.ans2Image1 = ans2Image1;
        this.ans2Image2 = ans2Image2;
        this.ans2Image3 = ans2Image3;
        this.ans2Image4 = ans2Image4;
        this.correctNum2 = correctNum2;
        this.solution2 = solution2;
        this.qn3Image = qn3Image;
        this.ans3Image1 = ans3Image1;
        this.ans3Image2 = ans3Image2;
        this.ans3Image3 = ans3Image3;
        this.ans3Image4 = ans3Image4;
        this.correctNum3 = correctNum3;
        this.solution3 = solution3;
    }

    public String getMode() {
        return mode;
    }

    public String getQnNum() {
        return qnNum;
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

    public int getAns1Image1() {
        return ans1Image1;
    }

    public int getAns1Image2() {
        return ans1Image2;
    }

    public int getCorrectNum1() {
        return correctNum1;
    }

    public int getQn2Image() {
        return qn2Image;
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

    public int getQn3Image() {
        return qn3Image;
    }

    public int getAns3Image1() {
        return ans3Image1;
    }

    public int getAns3Image2() {
        return ans3Image2;
    }

    public int getAns3Image3() {
        return ans3Image3;
    }

    public int getAns3Image4() {
        return ans3Image4;
    }

    public int getCorrectNum3() {
        return correctNum3;
    }

    public int getSolution1() {
        return solution1;
    }

    public int getSolution2() {
        return solution2;
    }

    public int getSolution3() {
        return solution3;
    }
}

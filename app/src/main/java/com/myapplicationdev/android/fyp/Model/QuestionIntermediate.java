package com.myapplicationdev.android.fyp.Model;

public class QuestionIntermediate {
    private String mode;
    private String qnNum;
    private int qnMainImage;
    private int qnCount;
    private String qn1Name;
    private int ans1Image1;
    private int ans1Image2;
    private int correctNum1;
    private String qn2Name;
    private int ans2Image1;
    private int ans2Image2;
    private int ans2Image3;
    private int correctNum2;
    private String qn3Name;
    private int ans3Image1;
    private int ans3Image2;
    private int ans3Image3;
    private int ans3Image4;
    private int correctNum3;

    public QuestionIntermediate(String mode, String qnNum, int qnMainImage, int qnCount, String qn1Name, int ans1Image1, int ans1Image2, int correctNum1, String qn2Name, int ans2Image1, int ans2Image2, int ans2Image3, int correctNum2, String qn3Name, int ans3Image1, int ans3Image2, int ans3Image3, int ans3Image4, int correctNum3) {
        this.mode = mode;
        this.qnNum = qnNum;
        this.qnMainImage = qnMainImage;
        this.qnCount = qnCount;
        this.qn1Name = qn1Name;
        this.ans1Image1 = ans1Image1;
        this.ans1Image2 = ans1Image2;
        this.correctNum1 = correctNum1;
        this.qn2Name = qn2Name;
        this.ans2Image1 = ans2Image1;
        this.ans2Image2 = ans2Image2;
        this.ans2Image3 = ans2Image3;
        this.correctNum2 = correctNum2;
        this.qn3Name = qn3Name;
        this.ans3Image1 = ans3Image1;
        this.ans3Image2 = ans3Image2;
        this.ans3Image3 = ans3Image3;
        this.ans3Image4 = ans3Image4;
        this.correctNum3 = correctNum3;
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

    public String getQn1Name() {
        return qn1Name;
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

    public String getQn2Name() {
        return qn2Name;
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

    public int getCorrectNum2() {
        return correctNum2;
    }

    public String getQn3Name() {
        return qn3Name;
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
}

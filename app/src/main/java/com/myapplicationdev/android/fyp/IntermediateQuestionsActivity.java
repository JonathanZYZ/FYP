package com.myapplicationdev.android.fyp;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Model.QuestionIntermediate;

import java.util.ArrayList;
public class IntermediateQuestionsActivity extends AppCompatActivity{

    Button btnStart;
    TextView tvQuestionsNumber, tvScore;
    RadioGroup groupQn, groupAns;
    RadioButton qn1Button, qn2Button, qn3Button, ans1Button,ans2Button,ans3Button,ans4Button;
    ImageView ivQuestion;
    ArrayList<QuestionIntermediate> al;
    int questionCounter, questionCountTotal;
    QuestionIntermediate currentQuestion;
    private int ans1,ans2,ans3;
    private int score;
    boolean answered;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_intermediate);

        btnStart = findViewById(R.id.btnStart_intermediate);
        tvQuestionsNumber = findViewById(R.id.tvQuestionsNumber_Intermediate);
        tvScore = findViewById(R.id.tvScore_Intermediate);
        groupQn = findViewById(R.id.radiogroup_Intermediate);
        qn1Button = findViewById(R.id.radiobutton_solvent_intermediate);
        qn2Button = findViewById(R.id.radiobutton_product_intermediate);
        qn3Button = findViewById(R.id.radiobutton_reaction_intermediate);
        groupAns = findViewById(R.id.radiogroup_MCQoption_intermediate);
        ans1Button = findViewById(R.id.radiobutton_option1_intermediate);
        ans2Button = findViewById(R.id.radiobutton_option2_intermediate);
        ans3Button = findViewById(R.id.radiobutton_option3_intermediate);
        ans4Button = findViewById(R.id.radiobutton_option4_intermediate);
        ivQuestion = findViewById(R.id.ivQuestions_Intermediate);

        al = new ArrayList<>();
        al.add(new QuestionIntermediate("intermediate", "1", R.drawable.question1_intermediate, 3, "Solvent" ,R.drawable.question1_intermediate_solvent_correct, R.drawable.question1_intermediate_solvent_incorrect,
                2, "Reaction Type",R.drawable.question1_intermediate_reaction_correct,R.drawable.question1_intermediate_reaction_incorrect,0,1,"Product",R.drawable.question1_intermediate_product1_incorrect,
                R.drawable.question1_intermediate_product2_incorrect,R.drawable.question1_intermediate_product3_correct,R.drawable.question1_intermediate_product4_incorrect,3));
        //
        al.add(new QuestionIntermediate("intermediate", "2", R.drawable.question2_intermediate, 3, "Solvent" ,R.drawable.question2_intermediate_solvent_correct, R.drawable.question2_intermediate_solvent_incorrect,
                1, "Reaction Type",R.drawable.question2_intermediate_reaction_incorrect,R.drawable.question2_intermediate_reaction_correct,0,2,"Starting Material",R.drawable.question2_intermediate_startingmaterial1_incorrect,
                R.drawable.question2_intermediate_startingmaterial2_incorrect,R.drawable.question2_intermediate_startingmaterial3_incorrect,R.drawable.question2_intermediate_startingmaterial_correct,4));
        //
        al.add(new QuestionIntermediate("intermediate", "3", R.drawable.question3_intermediate, 2, "Reaction Type" ,R.drawable.question3_intermediate_reaction_correct, R.drawable.question2_intermediate_reaction_incorrect,
                1, null,0,0,0,1,"Product",R.drawable.question3_intermediate_product1_incorrect,
                R.drawable.question3_intermediate_product_correct,R.drawable.question3_intermediate_product3_incorrect,R.drawable.question3_intermediate_product2_incorrect,2));
        //
        al.add(new QuestionIntermediate("intermediate", "4", R.drawable.question4_intermediate, 2, null ,0, 0,
                0, "Reaction Type",R.drawable.question4_intermediate_reaction_correct,R.drawable.question4_intermediate_reaction1_incorrect,R.drawable.question4_intermediate_reaction2_incorrect,1,"Product",R.drawable.question4_intermediate_product1_incorrect,
                R.drawable.question4_intermediate_product2_incorrect,R.drawable.question4_intermediate_product3_incorrect,R.drawable.question4_intermediate_product_correct,4));
        //
        al.add(new QuestionIntermediate("intermediate", "5", R.drawable.question5_intermediate, 2, "Leaving Group" ,R.drawable.question5_intermediate_leavinggroup_correct, R.drawable.question5_intermediate_leavinggroup_incorrect,
                1, null,0,0,0,0,"Product",R.drawable.question5_intermediate_product_correct,
                R.drawable.question5_intermediate_product1_incorrect,R.drawable.question5_intermediate_product2_incorrect,R.drawable.question5_intermediate_product3_incorrect,1));
        //
        al.add(new QuestionIntermediate("intermediate", "6", R.drawable.question6_intermediate, 1, "Leaving Group" ,R.drawable.question6_intermediate_leavinggroup_incorrect, R.drawable.question6_intermediate_leavinggroup_correct,
                1, null,0,0,0,0,null,0,
                0,0,0,0));
        //
        al.add(new QuestionIntermediate("intermediate", "7", R.drawable.question7_intermediate, 1, null ,0, 0,
                0, null,0,0,0,0,"Starting Material",R.drawable.question7_intermediate_startingmaterial1_incorrect,
                R.drawable.question7_intermediate_startingmaterial2_incorrect,R.drawable.question7_intermediate_startingmaterial_correct,R.drawable.question7_intermediate_startingmaterial3_incorrect,3));
        //
        al.add(new QuestionIntermediate("intermediate", "8", R.drawable.question8_intermediate, 1, null ,0, 0,
                0, null,0,0,0,0,"Product",R.drawable.question8_intermediate_product3_incorrect,
                R.drawable.question8_intermediate_product1_incorrect,R.drawable.question8_intermediate_product2_incorrect,R.drawable.question8_intermediate_product_correct,4));
        //
        al.add(new QuestionIntermediate("intermediate", "9", R.drawable.question9_intermediate, 1, null ,0, 0,
                0, null,0,0,0,0,"Product",R.drawable.question9_intermediate_product3_incorrect,
                R.drawable.question9_intermediate_product1_incorrect,R.drawable.question9_intermediate_product2_incorrect,R.drawable.question9_intermediate_product_correct,4));
        //
        al.add(new QuestionIntermediate("intermediate", "10", R.drawable.question10_intermediate, 1, null ,0, 0,
                0, null,0,0,0,0,"Starting Material",R.drawable.question10_intermediate_startingmaterial_correct,
                R.drawable.question10_intermediate_startingmaterial1_incorrect,R.drawable.question10_intermediate_startingmaterial2_incorrect,R.drawable.question10_intermediate_startingmaterial3_incorrect,1));
        //
        al.add(new QuestionIntermediate("intermediate", "11", R.drawable.question11_intermediate, 2, "Reaction Type" ,R.drawable.question11_intermediate_reaction_correct, R.drawable.question11_intermediate_reaction_incorrect,
                1, null,0,0,0,0,"Carbocation",R.drawable.question11_intermediate_carbocation1_incorrect,
                R.drawable.question11_intermediate_carbocation2_incorrect,R.drawable.question11_intermediate_carbocation_correct,R.drawable.question11_intermediate_carbocation3_incorrect,3));
        //
        al.add(new QuestionIntermediate("intermediate", "12", R.drawable.question12_intermediate, 2, "Solvent" ,R.drawable.question12_intermediate_solvent_correct, R.drawable.question12_intermediate_solvent_incorrect,
                1, "Reaction Type",R.drawable.question12_intermediate_reaction_correct,R.drawable.question12_intermediate_reaction_incorrect,0,1,null,0,
                0,0,0,0));
        //
        al.add(new QuestionIntermediate("intermediate", "13", R.drawable.question13_intermediate, 1, null ,0, 0,
                0, null,0,0,0,0,"Starting Material",R.drawable.question13_intermediate_startingmaterial_correct,
                R.drawable.question13_intermediate_startingmaterial1_incorrect,R.drawable.question13_intermediate_startingmaterial2_incorrect,R.drawable.question13_intermediate_startingmaterial3_incorrect,1));

        questionCountTotal = al.size();

        showNextQuestion();

        groupQn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (qn1Button.isChecked()){

                }
            }
        });


        btnStart.setOnClickListener(view -> {
            //TODO
//            Intent i = new Intent(QuestionsActivity.this, QuestionsAnswerActivity.class);
//            i.putExtra("question","Question 1");
//            startActivity(i);

            if (!answered) {
                if (qn1Button.isChecked() || qn2Button.isChecked() || qn3Button.isChecked() || ans1Button.isChecked() || ans2Button.isChecked() || ans3Button.isChecked() || ans4Button.isChecked()) {
                    checkAnswer();
                } else {
                    Toast.makeText(IntermediateQuestionsActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                }
            } else {
                showNextQuestion();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(groupQn.getCheckedRadioButtonId());
        int answer_number = groupQn.indexOfChild(rbSelected) + 1;

//        if (answer_number == currentQuestion.getAnswerNum()) {
//            AlertDialog.Builder myBuilder = new AlertDialog.Builder(IntermediateQuestionsActivity.this);
//            myBuilder.setTitle("Check Answer");
//            myBuilder.setMessage("You selected the correct answer!");
//            myBuilder.setCancelable(false);
//            myBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    showNextQuestion();
//                }
//            });
//
//            AlertDialog myDialog = myBuilder.create();
//            myDialog.show();
//
//            score++;
//            tvScore.setText("Score: " + score);
//
//        } else {
//            AlertDialog.Builder myBuilder = new AlertDialog.Builder(IntermediateQuestionsActivity.this);
//            myBuilder.setTitle("Check Answer");
//            myBuilder.setMessage("You selected the wrong answer!");
//            myBuilder.setCancelable(false);
//            myBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    showNextQuestion();
//                }
//            });
//
//            AlertDialog myDialog = myBuilder.create();
//            myDialog.show();
//        }
//
//        if (questionCounter < questionCountTotal) {
//            // btnStart.setText("Next Question");
//        } else {
//            btnStart.setText("Finish Quiz");
//        }
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        groupQn.clearCheck();
        groupAns.clearCheck();
        if (questionCounter < questionCountTotal) {
            currentQuestion = al.get(questionCounter);

            ivQuestion.setImageResource(currentQuestion.getQnMainImage());
            if (currentQuestion.getQnCount() == 1){
                qn2Button.setEnabled(false);
                qn3Button.setEnabled(false);
                if (currentQuestion.getQn1Name() != null){
                    qn1Button.setText(currentQuestion.getQn1Name());
                }else if (currentQuestion.getQn2Name() != null) {
                    qn1Button.setText(currentQuestion.getQn2Name());
                }else if (currentQuestion.getQn3Name() != null) {
                    qn1Button.setText(currentQuestion.getQn3Name());
                }
            }
            else if (currentQuestion.getQnCount() == 2){
                qn3Button.setEnabled(false);
                if (currentQuestion.getQn1Name() == null){
                    qn1Button.setText(currentQuestion.getQn2Name());
                    qn2Button.setText(currentQuestion.getQn3Name());
                }else if (currentQuestion.getQn2Name() == null){
                    qn1Button.setText(currentQuestion.getQn1Name());
                    qn2Button.setText(currentQuestion.getQn3Name());
                }else if (currentQuestion.getQn3Name() == null){
                    qn1Button.setText(currentQuestion.getQn1Name());
                    qn2Button.setText(currentQuestion.getQn2Name());
                }
            }
            else if (currentQuestion.getQnCount() ==3){
                qn1Button.setText(currentQuestion.getQn1Name());
                qn2Button.setText(currentQuestion.getQn2Name());
                qn3Button.setText(currentQuestion.getQn3Name());
            }

            groupQn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    if (qn1Button.isChecked()){
                        if (qn1Button.getText().equals(currentQuestion.getQn1Name())){
                            ans1Button.setBackgroundResource(currentQuestion.getAns1Image1());
                            ans2Button.setBackgroundResource(currentQuestion.getAns1Image2());
                            ans3Button.setEnabled(false);
                            ans4Button.setEnabled(false);
                        }
                        else if (qn1Button.getText().equals(currentQuestion.getQn2Name())){
                            if (currentQuestion.getAns2Image3() == 0){
                                ans1Button.setBackgroundResource(currentQuestion.getAns2Image1());
                                ans2Button.setBackgroundResource(currentQuestion.getAns2Image2());
                                ans3Button.setEnabled(false);
                                ans4Button.setEnabled(false);
                            }else {
                                ans1Button.setBackgroundResource(currentQuestion.getAns2Image1());
                                ans2Button.setBackgroundResource(currentQuestion.getAns2Image2());
                                ans3Button.setBackgroundResource(currentQuestion.getAns2Image3());
                                ans4Button.setEnabled(false);
                            }
                        }
                        else if (qn1Button.getText().equals(currentQuestion.getQn3Name())){
                            ans1Button.setBackgroundResource(currentQuestion.getAns3Image1());
                            ans2Button.setBackgroundResource(currentQuestion.getAns3Image2());
                            ans3Button.setBackgroundResource(currentQuestion.getAns3Image3());
                            ans4Button.setBackgroundResource(currentQuestion.getAns3Image4());
                        }
                    }

                    else if (qn2Button.isChecked()){
                        if (qn2Button.getText().equals(currentQuestion.getQn2Name())){
                            if (currentQuestion.getAns2Image3() == 0){
                                ans1Button.setBackgroundResource(currentQuestion.getAns2Image1());
                                ans2Button.setBackgroundResource(currentQuestion.getAns2Image2());
                                ans3Button.setEnabled(false);
                                ans4Button.setEnabled(false);
                            }else {
                                ans1Button.setBackgroundResource(currentQuestion.getAns2Image1());
                                ans2Button.setBackgroundResource(currentQuestion.getAns2Image2());
                                ans3Button.setBackgroundResource(currentQuestion.getAns2Image3());
                                ans4Button.setEnabled(false);
                            }
                        }else if (qn2Button.getText().equals(currentQuestion.getQn3Name())){
                            ans1Button.setBackgroundResource(currentQuestion.getAns3Image1());
                            ans2Button.setBackgroundResource(currentQuestion.getAns3Image2());
                            ans3Button.setBackgroundResource(currentQuestion.getAns3Image3());
                            ans4Button.setBackgroundResource(currentQuestion.getAns3Image4());
                        }
                    }

                    else if (qn3Button.isChecked()){
                        ans1Button.setBackgroundResource(currentQuestion.getAns3Image1());
                        ans2Button.setBackgroundResource(currentQuestion.getAns3Image2());
                        ans3Button.setBackgroundResource(currentQuestion.getAns3Image3());
                        ans4Button.setBackgroundResource(currentQuestion.getAns3Image4());
                    }
                }
            });

            questionCounter++;
            tvQuestionsNumber.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btnStart.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        finish();
    }
}



package com.myapplicationdev.android.fyp.Questions;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Models.QuestionIntermediate;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.util.ArrayList;

public class IntermediateQuestionsActivity extends AppCompatActivity {

    Button btnStart;
    TextView tvQuestionsNumber, tvScore;
    ImageView ivQuestion, ivChoice1, ivChoice2, ivChoice3;
    ArrayList<QuestionIntermediate> al;
    int questionCounter = 0, questionCountTotal;
    QuestionIntermediate currentQuestion;
    int ans1, ans2, ans3;
    int score = 0, numOfAnsForQn1, numOfAnsForQn2;
    int streak;
    boolean answered;
    DBHelper dbh = new DBHelper(IntermediateQuestionsActivity.this);
    SharedPreferences sharedPreferences;
    MediaPlayer choiceSound, correctSound, wrongSound, finishSound, backgroundMusic,buttonSound;
    EditText editText;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_intermediate);

        btnStart = findViewById(R.id.btnStart_intermediate);
        tvQuestionsNumber = findViewById(R.id.tvQuestionsNumber_Intermediate);
        tvScore = findViewById(R.id.tvScore_Intermediate);
        ivQuestion = findViewById(R.id.ivQuestions_Intermediate);
        ivChoice1 = findViewById(R.id.ivChoiceQn1);
        ivChoice2 = findViewById(R.id.ivChoiceQn2);
        ivChoice3 = findViewById(R.id.ivChoiceQn3);

        choiceSound = MediaPlayer.create(IntermediateQuestionsActivity.this, R.raw.answer_click);
        correctSound = MediaPlayer.create(IntermediateQuestionsActivity.this, R.raw.correct_answer);
        wrongSound = MediaPlayer.create(IntermediateQuestionsActivity.this, R.raw.wrong_answer);
        finishSound = MediaPlayer.create(IntermediateQuestionsActivity.this, R.raw.end_game);
        backgroundMusic = MediaPlayer.create(IntermediateQuestionsActivity.this, R.raw.background_music);
        buttonSound = MediaPlayer.create(IntermediateQuestionsActivity.this,R.raw.button_click);

        al = new ArrayList<>();
        al.add(new QuestionIntermediate("intermediate", "1", R.drawable.question1_intermediate, 3, R.drawable.hidden_qn_solvent, R.drawable.question1_intermediate_solvent_correct, R.drawable.question1_intermediate_solvent_incorrect,
                1, R.drawable.hidden_qn_reaction, R.drawable.question1_intermediate_reaction_correct, R.drawable.question1_intermediate_reaction_incorrect, 0, 1, R.drawable.hidden_qn_product, R.drawable.question1_intermediate_product1_incorrect,
                R.drawable.question1_intermediate_product2_incorrect, R.drawable.question1_intermediate_product3_correct, R.drawable.question1_intermediate_product4_incorrect, 3));
        //
        al.add(new QuestionIntermediate("intermediate", "2", R.drawable.question2_intermediate, 3, R.drawable.hidden_qn_solvent, R.drawable.question2_intermediate_solvent_correct, R.drawable.question2_intermediate_solvent_incorrect,
                1, R.drawable.hidden_qn_reaction, R.drawable.question2_intermediate_reaction_correct, R.drawable.question2_intermediate_reaction_incorrect, 0, 1, R.drawable.hidden_qn_startingmaterial, R.drawable.question2_intermediate_startingmaterial1_incorrect,
                R.drawable.question2_intermediate_startingmaterial2_incorrect, R.drawable.question2_intermediate_startingmaterial_correct, R.drawable.question2_intermediate_startingmaterial3_incorrect, 3));
        //
        al.add(new QuestionIntermediate("intermediate", "3", R.drawable.question3_intermediate, 2, R.drawable.hidden_qn_reaction, R.drawable.question3_intermediate_reaction_correct, R.drawable.question3_intermediate_reaction_incorrect,
                1, 0, 0, 0, 0, 0, R.drawable.hidden_qn_product, R.drawable.question3_intermediate_product1_incorrect,
                R.drawable.question3_intermediate_product2_incorrect, R.drawable.question3_intermediate_product_correct, R.drawable.question3_intermediate_product3_incorrect, 3));
        //
        al.add(new QuestionIntermediate("intermediate", "4", R.drawable.question4_intermediate, 2, 0, 0, 0,
                0, R.drawable.hidden_qn_reaction, R.drawable.question4_intermediate_reaction_correct, R.drawable.question4_intermediate_reaction1_incorrect, R.drawable.question4_intermediate_reaction2_incorrect, 1, R.drawable.hidden_qn_product, R.drawable.question4_intermediate_product1_incorrect,
                R.drawable.question4_intermediate_product2_incorrect, R.drawable.question4_intermediate_product3_incorrect, R.drawable.question4_intermediate_product_correct, 4));
        //
        al.add(new QuestionIntermediate("intermediate", "5", R.drawable.question5_intermediate, 2, R.drawable.hidden_qn_leavinggroup, R.drawable.question5_intermediate_leavinggroup_incorrect, R.drawable.question5_intermediate_leavinggroup_correct,
                2, 0, 0, 0, 0, 0, R.drawable.hidden_qn_product, R.drawable.question5_intermediate_product1_incorrect,
                R.drawable.question5_intermediate_product2_incorrect, R.drawable.question5_intermediate_product3_incorrect, R.drawable.question5_intermediate_product_correct, 4));
        //
        al.add(new QuestionIntermediate("intermediate", "6", R.drawable.question6_intermediate, 1, R.drawable.hidden_qn_leavinggroup, R.drawable.question6_intermediate_leavinggroup_incorrect, R.drawable.question6_intermediate_leavinggroup_correct,
                2, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0));
        //
        al.add(new QuestionIntermediate("intermediate", "7", R.drawable.question7_intermediate, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0, R.drawable.hidden_qn_startingmaterial, R.drawable.question7_intermediate_startingmaterial_correct,
                R.drawable.question7_intermediate_startingmaterial1_incorrect, R.drawable.question7_intermediate_startingmaterial2_incorrect, R.drawable.question7_intermediate_startingmaterial3_incorrect, 1));
        //
        al.add(new QuestionIntermediate("intermediate", "8", R.drawable.question8_intermediate, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0, R.drawable.hidden_qn_product, R.drawable.question8_intermediate_product_correct,
                R.drawable.question8_intermediate_product1_incorrect, R.drawable.question8_intermediate_product2_incorrect, R.drawable.question8_intermediate_product3_incorrect, 1));
        //
        al.add(new QuestionIntermediate("intermediate", "9", R.drawable.question9_intermediate, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0, R.drawable.hidden_qn_product, R.drawable.question9_intermediate_product3_incorrect,
                R.drawable.question9_intermediate_product1_incorrect, R.drawable.question9_intermediate_product2_incorrect, R.drawable.question9_intermediate_product_correct, 4));
        //
        al.add(new QuestionIntermediate("intermediate", "10", R.drawable.question10_intermediate, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0, R.drawable.hidden_qn_startingmaterial, R.drawable.question10_intermediate_startingmaterial_correct,
                R.drawable.question10_intermediate_startingmaterial1_incorrect, R.drawable.question10_intermediate_startingmaterial2_incorrect, R.drawable.question10_intermediate_startingmaterial3_incorrect, 1));
        //
        al.add(new QuestionIntermediate("intermediate", "11", R.drawable.question11_intermediate, 2, R.drawable.hidden_qn_reaction, R.drawable.question11_intermediate_reaction_correct, R.drawable.question11_intermediate_reaction_incorrect,
                1, 0, 0, 0, 0, 0, R.drawable.hidden_qn_carbocation, R.drawable.question11_intermediate_carbocation1_incorrect,
                R.drawable.question11_intermediate_carbocation2_incorrect, R.drawable.question11_intermediate_carbocation_correct, R.drawable.question11_intermediate_carbocation3_incorrect, 3));
        //
        al.add(new QuestionIntermediate("intermediate", "12", R.drawable.question12_intermediate, 2, R.drawable.hidden_qn_solvent, R.drawable.question12_intermediate_solvent_correct, R.drawable.question12_intermediate_solvent_incorrect,
                1, R.drawable.hidden_qn_reaction, R.drawable.question12_intermediate_reaction_correct, R.drawable.question12_intermediate_reaction_incorrect, 0, 1, 0, 0,
                0, 0, 0, 0));
        //
        al.add(new QuestionIntermediate("intermediate", "13", R.drawable.question13_intermediate, 1, 0, 0, 0,
                0, 0, 0, 0, 0, 0, R.drawable.hidden_qn_startingmaterial, R.drawable.question13_intermediate_startingmaterial_correct,
                R.drawable.question13_intermediate_startingmaterial1_incorrect, R.drawable.question13_intermediate_startingmaterial2_incorrect, R.drawable.question13_intermediate_startingmaterial3_incorrect, 1));
        //
        al.add(new QuestionIntermediate("intermediate", "14", R.drawable.question14_intermediate, 1, R.drawable.hidden_qn_solvent, R.drawable.question14_intermediate_correct, R.drawable.question14_intermediate_incorrect,
                1, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0));

        questionCountTotal = al.size();
        Intent i = getIntent();
        int currentQnNum = i.getIntExtra("questionNum", 0);
        int currentScore = i.getIntExtra("score", 0);
        if (currentQnNum != 0 && currentScore != 0) {
            questionCounter = currentQnNum;
            score = currentScore;
        }
        tvQuestionsNumber.setText("Question: " + questionCounter + "/" + questionCountTotal);
        tvScore.setText("Score: " + score);
        showNextQuestion();


        btnStart.setOnClickListener(view -> {
            //TODO
//            Intent i = new Intent(QuestionsActivity.this, QuestionsAnswerActivity.class);
//            i.putExtra("question","Question 1");
//            startActivity(i);
            buttonSound.start();
            if (!answered) {

                checkAnswer();

            } else {
                showNextQuestion();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer() {
        answered = true;
        if (ans1 == 0 && ans2 == 0 && ans3 == 0) {
            answered = false;
            wrongSound.start();
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(IntermediateQuestionsActivity.this);
            myBuilder.setTitle("No Input!");
            myBuilder.setMessage("Click on the question mark to input your answer");
            myBuilder.setCancelable(false);
            myBuilder.setPositiveButton("Input Answer", (dialogInterface, i) -> dialogInterface.dismiss());

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        } else {
            if (questionCounter < questionCountTotal) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(IntermediateQuestionsActivity.this);
                myBuilder.setTitle("Sorry");
                if (currentQuestion.getQnCount() == 3) {
                    if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2() && ans3 == currentQuestion.getCorrectNum3()) {
                        correctSound.start();
                        streak += 1;
                        score++;
                        if (streak == 5) {
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You have answered 5 questions correctly in a row! We would like to test you further by bringing you to the Advanced Level! GoodLuck!");
                            myBuilder.setCancelable(false);
                            //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                            myBuilder.setPositiveButton("Proceed to Advanced Mode", (dialogInterface, i) -> {
                                Intent intent = new Intent(IntermediateQuestionsActivity.this, AdvancedQuestionsActivity.class);
                                intent.putExtra("score", score);
                                intent.putExtra("questionNum", questionCounter + 1);
                                startActivity(intent);
                            });

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                        } else {
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You selected the correct answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                            tvScore.setText("Score: " + score);
                        }


                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2() && ans3 == currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for Question 1!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2() && ans3 == currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for Question 2!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2() && ans3 != currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for Question 3!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2() && ans3 == currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for Question 1 and 2!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2() && ans3 != currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for Question 1 and 3!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2() && ans3 != currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for Question 2 and 3!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2() && ans3 != currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        streak = 0;
                        myBuilder.setMessage("You selected the wrong answer for all Questions!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }

                } else if (currentQuestion.getQnCount() == 2) {
                    if (currentQuestion.getQn1Image() == 0) {
                        if (ans1 == currentQuestion.getCorrectNum2() && ans2 == currentQuestion.getCorrectNum3()) {
                            correctSound.start();
                            streak += 1;
                            score++;
                            if (streak == 5) {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You have answered 5 questions correctly in a row! We would like to test you further by bringing you to the Advanced Level! GoodLuck!");
                                myBuilder.setCancelable(false);
                                //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                                myBuilder.setPositiveButton("Proceed to Advanced Mode", (dialogInterface, i) -> {
                                    Intent intent = new Intent(IntermediateQuestionsActivity.this, AdvancedQuestionsActivity.class);
                                    intent.putExtra("score", score);
                                    intent.putExtra("questionNum", questionCounter + 1);
                                    startActivity(intent);
                                });

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                            } else {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You selected the correct answer!");
                                myBuilder.setCancelable(false);
                                myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                                tvScore.setText("Score: " + score);
                            }
                        } else if (ans1 != currentQuestion.getCorrectNum2() && ans2 == currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer for Question 1!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else if (ans1 == currentQuestion.getCorrectNum2() && ans2 != currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer for Question 2!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer for all Questions!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    } else if (currentQuestion.getQn2Image() == 0) {
                        if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum3()) {
                            correctSound.start();
                            streak += 1;
                            score++;

                            if (streak == 5) {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You have answered 5 questions correctly in a row! We would like to test you further by bringing you to the Advanced Level! GoodLuck!");
                                myBuilder.setCancelable(false);
                                //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                                myBuilder.setPositiveButton("Proceed to Advanced Mode", (dialogInterface, i) -> {
                                    Intent intent = new Intent(IntermediateQuestionsActivity.this, AdvancedQuestionsActivity.class);
                                    intent.putExtra("score", score);
                                    intent.putExtra("questionNum", questionCounter + 1);
                                    startActivity(intent);
                                });

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                            } else {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You selected the correct answer!");
                                myBuilder.setCancelable(false);
                                myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                                tvScore.setText("Score: " + score);
                            }
                        } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer for Question 1!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer for Question 2!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer for all Questions!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    } else if (currentQuestion.getQn3Image() == 0) {
                        if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2()) {
                            correctSound.start();
                            streak += 1;
                            score++;
                            if (streak == 5) {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You have answered 5 questions correctly in a row! We would like to test you further by bringing you to the Advanced Level! GoodLuck!");
                                myBuilder.setCancelable(false);
                                //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                                myBuilder.setPositiveButton("Proceed to Advanced Mode", (dialogInterface, i) -> {
                                    Intent intent = new Intent(IntermediateQuestionsActivity.this, AdvancedQuestionsActivity.class);
                                    intent.putExtra("score", score);
                                    intent.putExtra("questionNum", questionCounter + 1);
                                    startActivity(intent);
                                });

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                            } else {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You selected the correct answer!");
                                myBuilder.setCancelable(false);
                                myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                                tvScore.setText("Score: " + score);
                            }
                        } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2()) {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer for Question 1!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2()) {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer for Question 2!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer for all Questions!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    }


                } else if (currentQuestion.getQnCount() == 1) {
                    if (currentQuestion.getQn1Image() != 0) {
                        if (ans1 == currentQuestion.getCorrectNum1()) {
                            correctSound.start();
                            streak += 1;
                            score++;
                            if (streak == 5) {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You have answered 5 questions correctly in a row! We would like to test you further by bringing you to the Advanced Level! GoodLuck!");
                                myBuilder.setCancelable(false);
                                //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                                myBuilder.setPositiveButton("Proceed to Advanced Mode", (dialogInterface, i) -> {
                                    Intent intent = new Intent(IntermediateQuestionsActivity.this, AdvancedQuestionsActivity.class);
                                    intent.putExtra("score", score);
                                    intent.putExtra("questionNum", questionCounter + 1);
                                    startActivity(intent);
                                });

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                            } else {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You selected the correct answer!");
                                myBuilder.setCancelable(false);
                                myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                                tvScore.setText("Score: " + score);
                            }
                        } else if (ans1 != currentQuestion.getCorrectNum1()) {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    } else if (currentQuestion.getQn2Image() != 0) {
                        if (ans1 == currentQuestion.getCorrectNum2()) {
                            correctSound.start();
                            streak += 1;
                            score++;
                            if (streak == 5) {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You have answered 5 questions correctly in a row! We would like to test you further by bringing you to the Advanced Level! GoodLuck!");
                                myBuilder.setCancelable(false);
                                //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                                myBuilder.setPositiveButton("Proceed to Advanced Mode", (dialogInterface, i) -> {
                                    Intent intent = new Intent(IntermediateQuestionsActivity.this, AdvancedQuestionsActivity.class);
                                    intent.putExtra("score", score);
                                    intent.putExtra("questionNum", questionCounter + 1);
                                    startActivity(intent);
                                });

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();


                            } else {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You selected the correct answer!");
                                myBuilder.setCancelable(false);
                                myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                                tvScore.setText("Score: " + score);
                            }
                        } else if (ans1 != currentQuestion.getCorrectNum2()) {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    } else if (currentQuestion.getQn3Image() != 0) {
                        if (ans1 == currentQuestion.getCorrectNum3()) {
                            correctSound.start();
                            streak += 1;
                            score++;
                            if (streak == 5) {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You have answered 5 questions correctly in a row! We would like to test you further by bringing you to the Advanced Level! GoodLuck!");
                                myBuilder.setCancelable(false);
                                //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                                myBuilder.setPositiveButton("Proceed to Advanced Mode", (dialogInterface, i) -> {
                                    Intent intent = new Intent(IntermediateQuestionsActivity.this, AdvancedQuestionsActivity.class);
                                    intent.putExtra("score", score);
                                    intent.putExtra("questionNum", questionCounter + 1);
                                    startActivity(intent);
                                });

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                            } else {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You selected the correct answer!");
                                myBuilder.setCancelable(false);
                                myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                                AlertDialog myDialog = myBuilder.create();
                                myDialog.show();

                                tvScore.setText("Score: " + score);
                            }
                        } else if (ans1 != currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            streak = 0;
                            myBuilder.setMessage("You selected the wrong answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Next", (dialogInterface, i) -> showNextQuestion());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    }
                }


                // btnStart.setText("Next Question");
            } else {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(IntermediateQuestionsActivity.this);
                myBuilder.setTitle("Sorry");
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                myBuilder.setView(customLayout);
                editText = customLayout.findViewById(R.id.et_text);
                editText.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                if (currentQuestion.getQnCount() == 3) {
                    if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2() && ans3 == currentQuestion.getCorrectNum3()) {
                        correctSound.start();
                        myBuilder.setTitle("Congratulations!!");
                        myBuilder.setMessage("You selected the correct answer!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();

                        score++;
                        tvScore.setText("Score: " + score);
                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2() && ans3 == currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        myBuilder.setMessage("You selected the wrong answer for Question 1!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2() && ans3 == currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        myBuilder.setMessage("You selected the wrong answer for Question 2!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2() && ans3 != currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        myBuilder.setMessage("You selected the wrong answer for Question 3!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2() && ans3 == currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        myBuilder.setMessage("You selected the wrong answer for Question 1 and 2!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2() && ans3 != currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        myBuilder.setMessage("You selected the wrong answer for Question 1 and 3!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2() && ans3 != currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        myBuilder.setMessage("You selected the wrong answer for Question 2 and 3!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2() && ans3 != currentQuestion.getCorrectNum3()) {
                        wrongSound.start();
                        myBuilder.setMessage("You selected the wrong answer for all Questions!");
                        myBuilder.setCancelable(false);
                        myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                        AlertDialog myDialog = myBuilder.create();
                        myDialog.show();
                    }

                } else if (currentQuestion.getQnCount() == 2) {
                    if (currentQuestion.getQn1Image() == 0) {
                        if (ans1 == currentQuestion.getCorrectNum2() && ans2 == currentQuestion.getCorrectNum3()) {
                            correctSound.start();
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You selected the correct answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                            score++;
                            tvScore.setText("Score: " + score);
                        } else if (ans1 != currentQuestion.getCorrectNum2() && ans2 == currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer for Question 1!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else if (ans1 == currentQuestion.getCorrectNum2() && ans2 != currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer for Question 2!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer for all Questions!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    } else if (currentQuestion.getQn2Image() == 0) {
                        if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum3()) {
                            correctSound.start();
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You selected the correct answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                            score++;
                            tvScore.setText("Score: " + score);
                        } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer for Question 1!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer for Question 2!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer for all Questions!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    } else if (currentQuestion.getQn3Image() == 0) {
                        if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2()) {
                            correctSound.start();
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You selected the correct answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                            score++;
                            tvScore.setText("Score: " + score);
                        } else if (ans1 != currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2()) {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer for Question 1!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else if (ans1 == currentQuestion.getCorrectNum1() && ans2 != currentQuestion.getCorrectNum2()) {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer for Question 2!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        } else {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer for all Questions!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    }


                } else if (currentQuestion.getQnCount() == 1) {
                    if (currentQuestion.getQn1Image() != 0) {
                        if (ans1 == currentQuestion.getCorrectNum1()) {
                            correctSound.start();
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You selected the correct answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                            score++;
                            tvScore.setText("Score: " + score);
                        } else if (ans1 != currentQuestion.getCorrectNum1()) {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    } else if (currentQuestion.getQn2Image() != 0) {
                        if (ans1 == currentQuestion.getCorrectNum2()) {
                            correctSound.start();
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You selected the correct answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                            score++;
                            tvScore.setText("Score: " + score);
                        } else if (ans1 != currentQuestion.getCorrectNum2()) {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    } else if (currentQuestion.getQn3Image() != 0) {
                        if (ans1 == currentQuestion.getCorrectNum3()) {
                            correctSound.start();
                            myBuilder.setTitle("Congratulations!!");
                            myBuilder.setMessage("You selected the correct answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();

                            score++;
                            tvScore.setText("Score: " + score);
                        } else if (ans1 != currentQuestion.getCorrectNum3()) {
                            wrongSound.start();
                            myBuilder.setMessage("You selected the wrong answer!");
                            myBuilder.setCancelable(false);
                            myBuilder.setPositiveButton("Check Results", (dialogInterface, i) -> finishQuiz());

                            AlertDialog myDialog = myBuilder.create();
                            myDialog.show();
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        choiceSound.start();
        ans1 = 0;
        ans2 = 0;
        ans3 = 0;
        ivChoice1.setEnabled(true);
        ivChoice2.setEnabled(true);
        ivChoice3.setEnabled(true);
        if (questionCounter < questionCountTotal) {
            currentQuestion = al.get(questionCounter);

            ivQuestion.setImageResource(currentQuestion.getQnMainImage());
            if (currentQuestion.getQnCount() == 1) {
                ivChoice2.setImageResource(android.R.color.transparent);
                ivChoice2.setEnabled(false);
                ivChoice3.setImageResource(android.R.color.transparent);
                ivChoice3.setEnabled(false);
                if (currentQuestion.getQn1Image() != 0) {
                    ivChoice1.setImageResource(currentQuestion.getQn1Image());
                    numOfAnsForQn1 = 2;
                } else if (currentQuestion.getQn2Image() != 0) {
                    ivChoice1.setImageResource(currentQuestion.getQn2Image());
                    numOfAnsForQn1 = 3;
                } else if (currentQuestion.getQn3Image() != 0) {
                    ivChoice1.setImageResource(currentQuestion.getQn3Image());
                    numOfAnsForQn1 = 4;
                }
            } else if (currentQuestion.getQnCount() == 2) {
                ivChoice3.setImageResource(android.R.color.transparent);
                ivChoice3.setEnabled(false);
                if (currentQuestion.getQn1Image() == 0) {
                    ivChoice1.setImageResource(currentQuestion.getQn2Image());
                    numOfAnsForQn1 = 3;
                    ivChoice2.setImageResource(currentQuestion.getQn3Image());
                    numOfAnsForQn2 = 4;
                } else if (currentQuestion.getQn2Image() == 0) {
                    ivChoice1.setImageResource(currentQuestion.getQn1Image());
                    numOfAnsForQn1 = 2;
                    ivChoice2.setImageResource(currentQuestion.getQn3Image());
                    numOfAnsForQn2 = 4;
                } else if (currentQuestion.getQn3Image() == 0) {
                    ivChoice1.setImageResource(currentQuestion.getQn1Image());
                    numOfAnsForQn1 = 2;
                    ivChoice2.setImageResource(currentQuestion.getQn2Image());
                    numOfAnsForQn2 = 3;
                }
            } else if (currentQuestion.getQnCount() == 3) {
                ivChoice1.setImageResource(currentQuestion.getQn1Image());
                numOfAnsForQn1 = 2;
                ivChoice2.setImageResource(currentQuestion.getQn2Image());
                numOfAnsForQn2 = 3;
                ivChoice3.setImageResource(currentQuestion.getQn3Image());
            }


            ivChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choiceSound.start();
                    MyCustomAlertDialog(1, numOfAnsForQn1);
                }
            });
            ivChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choiceSound.start();
                    MyCustomAlertDialog(2, numOfAnsForQn2);
                }
            });
            ivChoice3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choiceSound.start();
                    MyCustomAlertDialog(3, 4);
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

    private void MyCustomAlertDialog(int qnNum, int numOfAns) {
        final Dialog MyDialog = new Dialog(IntermediateQuestionsActivity.this);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.custom_alert_dialog);

        ImageView ivUserChoice1 = MyDialog.findViewById(R.id.ivUserChoice1);
        ImageView ivUserChoice2 = MyDialog.findViewById(R.id.ivUserChoice2);
        ImageView ivUserChoice3 = MyDialog.findViewById(R.id.ivUserChoice3);
        ImageView ivUserChoice4 = MyDialog.findViewById(R.id.ivUserChoice4);

        if (qnNum == 1) {
            if (numOfAns == 2) {
                ivUserChoice3.setEnabled(false);
                ivUserChoice3.setImageResource(android.R.color.transparent);
                ivUserChoice4.setEnabled(false);
                ivUserChoice4.setImageResource(android.R.color.transparent);
                //
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns1Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns1Image2());
            } else if (numOfAns == 3) {
                ivUserChoice4.setEnabled(false);
                ivUserChoice4.setImageResource(android.R.color.transparent);
                //
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns2Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns2Image2());
                if (currentQuestion.getAns2Image3() == 0) {
                    ivUserChoice3.setEnabled(false);
                    ivUserChoice3.setImageResource(android.R.color.transparent);
                } else {
                    ivUserChoice3.setEnabled(true);
                    ivUserChoice3.setImageResource(currentQuestion.getAns2Image3());
                }
            } else if (numOfAns == 4) {
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns3Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns3Image2());
                ivUserChoice3.setEnabled(true);
                ivUserChoice3.setImageResource(currentQuestion.getAns3Image3());
                ivUserChoice4.setEnabled(true);
                ivUserChoice4.setImageResource(currentQuestion.getAns3Image4());
            }


        } else if (qnNum == 2) {
            if (numOfAns == 3) {
                ivUserChoice4.setEnabled(false);
                ivUserChoice4.setImageResource(android.R.color.transparent);
                //
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns2Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns2Image2());
                if (currentQuestion.getAns2Image3() == 0) {
                    ivUserChoice3.setEnabled(false);
                    ivUserChoice3.setImageResource(android.R.color.transparent);
                } else {
                    ivUserChoice3.setEnabled(true);
                    ivUserChoice3.setImageResource(currentQuestion.getAns2Image3());
                }
            } else if (numOfAns == 4) {
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns3Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns3Image2());
                ivUserChoice3.setEnabled(true);
                ivUserChoice3.setImageResource(currentQuestion.getAns3Image3());
                ivUserChoice4.setEnabled(true);
                ivUserChoice4.setImageResource(currentQuestion.getAns3Image4());
            }

        } else if (qnNum == 3) {
            ivUserChoice1.setEnabled(true);
            ivUserChoice1.setImageResource(currentQuestion.getAns3Image1());
            ivUserChoice2.setEnabled(true);
            ivUserChoice2.setImageResource(currentQuestion.getAns3Image2());
            ivUserChoice3.setEnabled(true);
            ivUserChoice3.setImageResource(currentQuestion.getAns3Image3());
            ivUserChoice4.setEnabled(true);
            ivUserChoice4.setImageResource(currentQuestion.getAns3Image4());
        }

        ivUserChoice1.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {
                if (numOfAns == 2) {
                    ans1 = 1;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image1());
                } else if (numOfAns == 3) {
                    ans1 = 1;
                    ivChoice1.setImageResource(currentQuestion.getAns2Image1());
                } else if (numOfAns == 4) {
                    ans1 = 1;
                    ivChoice1.setImageResource(currentQuestion.getAns3Image1());
                }

            } else if (qnNum == 2) {
                if (numOfAns == 3) {
                    ans2 = 1;
                    ivChoice2.setImageResource(currentQuestion.getAns2Image1());
                } else if (numOfAns == 4) {
                    ans2 = 1;
                    ivChoice2.setImageResource(currentQuestion.getAns3Image1());
                }

            } else if (qnNum == 3) {
                ans3 = 1;
                ivChoice3.setImageResource(currentQuestion.getAns3Image1());
            }
            MyDialog.cancel();
        });

        ivUserChoice2.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {
                if (numOfAns == 2) {
                    ans1 = 2;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image2());
                } else if (numOfAns == 3) {
                    ans1 = 2;
                    ivChoice1.setImageResource(currentQuestion.getAns2Image2());
                } else if (numOfAns == 4) {
                    ans1 = 2;
                    ivChoice1.setImageResource(currentQuestion.getAns3Image2());
                }

            } else if (qnNum == 2) {
                if (numOfAns == 3) {
                    ans2 = 2;
                    ivChoice2.setImageResource(currentQuestion.getAns2Image2());
                } else if (numOfAns == 4) {
                    ans2 = 2;
                    ivChoice2.setImageResource(currentQuestion.getAns3Image2());
                }

            } else if (qnNum == 3) {
                ans3 = 2;
                ivChoice3.setImageResource(currentQuestion.getAns3Image2());
            }
            MyDialog.cancel();
        });

        ivUserChoice3.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {
                if (numOfAns == 3) {
                    ans1 = 3;
                    ivChoice1.setImageResource(currentQuestion.getAns2Image3());
                } else if (numOfAns == 4) {
                    ans1 = 3;
                    ivChoice1.setImageResource(currentQuestion.getAns3Image3());
                }

            } else if (qnNum == 2) {
                if (numOfAns == 3) {
                    ans2 = 3;
                    ivChoice2.setImageResource(currentQuestion.getAns2Image3());
                } else if (numOfAns == 4) {
                    ans2 = 3;
                    ivChoice2.setImageResource(currentQuestion.getAns3Image3());
                }

            } else if (qnNum == 3) {
                ans3 = 3;
                ivChoice3.setImageResource(currentQuestion.getAns3Image3());
            }
            MyDialog.cancel();
        });

        ivUserChoice4.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {

                ans1 = 4;
                ivChoice1.setImageResource(currentQuestion.getAns3Image4());


            } else if (qnNum == 2) {

                ans2 = 4;
                ivChoice2.setImageResource(currentQuestion.getAns3Image4());


            } else if (qnNum == 3) {
                ans3 = 4;
                ivChoice3.setImageResource(currentQuestion.getAns3Image4());
            }
            MyDialog.cancel();

        });

        MyDialog.show();

    }

    private void finishQuiz() {
        if (dbh.getAllScoreBoard().isEmpty()) {
            finishSound.start();
            Intent i = new Intent(IntermediateQuestionsActivity.this, ResultActivity.class);
            i.putExtra("score", score);
            i.putExtra("difficulty", currentQuestion.getMode());
            i.putExtra("username", editText.getText().toString());
            Log.i("Test", "check");
            startActivity(i);
            finish();
        } else {
            ArrayList<String> namesInScoreboard = dbh.getNameInScoreBoard();
            for (int x = 0; x < namesInScoreboard.size(); x++) {
                if (editText.getText().toString().equalsIgnoreCase(namesInScoreboard.get(x))) {
                    wrongSound.start();
                    editText.setText("");
                    Toast.makeText(IntermediateQuestionsActivity.this, "Name already exists. Use a different name.", Toast.LENGTH_SHORT).show();
                } else {
                    finishSound.start();
                    Intent i = new Intent(IntermediateQuestionsActivity.this, ResultActivity.class);
                    i.putExtra("score", score);
                    i.putExtra("difficulty", currentQuestion.getMode());
                    i.putExtra("username", editText.getText().toString());
                    startActivity(i);
                    finish();
                }
            }
        }
    }
}



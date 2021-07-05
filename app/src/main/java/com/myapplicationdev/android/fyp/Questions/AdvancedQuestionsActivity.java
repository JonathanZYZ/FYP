package com.myapplicationdev.android.fyp.Questions;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Model.QuestionAdvanced;
import com.myapplicationdev.android.fyp.Model.QuestionIntermediate;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.ResultActivity;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.util.ArrayList;

public class AdvancedQuestionsActivity extends AppCompatActivity {

    Button btnStart;
    TextView tvQuestionsNumber, tvScore;
    ImageView ivQuestion, ivChoice1, ivChoice2,ivDidYouKnow;
    QuestionAdvanced currentQuestion;
    ArrayList<QuestionAdvanced> al;
    DBHelper dbh = new DBHelper(AdvancedQuestionsActivity.this);
    SharedPreferences sharedPreferences;
    int questionCounter, questionCountTotal;
    int ans1, ans2,streak;
    int score, numOfAnsForQn1, numOfAnsForQn2;
    boolean answered;
    MediaPlayer choiceSound,correctSound,wrongSound,finishSound,backgroundMusic;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_advanced);


        btnStart = findViewById(R.id.btnStart_advanced);
        tvQuestionsNumber = findViewById(R.id.tvQuestionsNumber_Advanced);
        tvScore = findViewById(R.id.tvScore_Advanced);
        ivQuestion = findViewById(R.id.ivQuestions_Advanced);
        ivChoice1 = findViewById(R.id.ivChoiceAdvancedQn1);
        ivChoice2 = findViewById(R.id.ivChoiceAdvancedQn2);
        ivDidYouKnow = findViewById(R.id.ivDidYouKnow);

        choiceSound = MediaPlayer.create(AdvancedQuestionsActivity.this,R.raw.answer_click);
        correctSound = MediaPlayer.create(AdvancedQuestionsActivity.this,R.raw.correct_answer);
        wrongSound = MediaPlayer.create(AdvancedQuestionsActivity.this,R.raw.wrong_answer);
        finishSound = MediaPlayer.create(AdvancedQuestionsActivity.this,R.raw.end_game);
        backgroundMusic = MediaPlayer.create(AdvancedQuestionsActivity.this,R.raw.background_music);

//  TODO: When the user clicks the "did you know?" icon on the screen, text messages are prepared for them.
        String didYouKnowIconMsgQ1 = "Tamoxifen's main skeleton is a tertiary alcohol. The starting material is treated with sulfuric acid, and acid catalyzed dehydration takes place. Alcohol group protonation makes it a good leaving group, and E1 reactions occur when a tertiary carbocation is formed prior to Tamoxifen formation (Z isomer).\n" +
                "\n" +
                "Tamoxifen is an estrogen receptor modulator (SERM).\n" +
                "\n" +
                "Tamoxifen is a widely used drug in the treatment of breast cancer (ER positive breast cancer).";

        String didYouKnowIconMsgQ2 = "Newer muscle relaxants, such as atracurium, have a built-in functional group that allows the drug to be rapidly metabolised or changed into an inactive form via the E2 reaction of the drug's quaternary ammonium group.\n" +
                "\n" +
                "In the presence of normal blood pH and temperature, spontaneous degradation occurs. There are no nucleophiles present.";
        String didYouKnowIconMsgQ3 = "The E1 reaction is involved in the biosynthesis of geraniol in roses.\n" +
                "A carbocation serves as the starting material.\n" +
                "\n" +
                "Geranyl diphosphate can be synthesized using a strong base and a protic solvent.\n" +
                "\n" +
                "I've lost a CH3, and I'm not sure where the OPP is coming from.";
        String didYouKnowIconMsgQ4 = "There is no hint for this question.";
        String didYouKnowIconMsgQ5 = "There is no hint for this question.";
        String didYouKnowIconMsgQ6 = "There is no hint for this question.";
        String didYouKnowIconMsgQ7 = "There is no hint for this question.";
        String didYouKnowIconMsgQ8 = "There is no hint for this question.";
        String didYouKnowIconMsgQ9 = "There is no hint for this question.";

        al = new ArrayList<>();

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
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    @SuppressLint("SetTextI18n")
    public void checkAnswer() {
        answered = true;
        if (ans1 == 0 && ans2 == 0) {
            wrongSound.start();
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(AdvancedQuestionsActivity.this);
            myBuilder.setTitle("No Input!");
            myBuilder.setMessage("Click on the question mark to input your answer");
            myBuilder.setCancelable(false);
            myBuilder.setPositiveButton("Input Answer", (dialogInterface, i) -> dialogInterface.dismiss());

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        } else {
            if (questionCounter < questionCountTotal) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(AdvancedQuestionsActivity.this);
                myBuilder.setTitle("Sorry");
                if (currentQuestion.getQnCount() == 2) {
                        if (ans1 == currentQuestion.getCorrectNum1() && ans2 == currentQuestion.getCorrectNum2()) {
                            correctSound.start();
                            streak += 1;
                            score++;
                            if (streak == 5) {
                                myBuilder.setTitle("Congratulations!!");
                                myBuilder.setMessage("You have answered 5 questions correctly in a row! Would you like to continue or check your results?");
                                myBuilder.setCancelable(false);
                                //myBuilder.setPositiveButton("Next", (dialogInterface, i) -> Intent intent = new Intent(IntermediateQuestionsActivity.this,AdvancedQuestionsActivity));
                                myBuilder.setPositiveButton("Continue", (dialogInterface, i) -> {
                                    streak = 0;
                                    showNextQuestion();
                                });
                                myBuilder.setNegativeButton("Check Results", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finishQuiz();
                                    }
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
                                myBuilder.setPositiveButton("Continue", (dialogInterface, i) -> {
                                    streak = 0;
                                    showNextQuestion();
                                });
                                myBuilder.setNegativeButton("Check Results", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finishQuiz();
                                    }
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
                    }
                }


                // btnStart.setText("Next Question");
            } else {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(AdvancedQuestionsActivity.this);
                myBuilder.setTitle("Sorry");
                if (currentQuestion.getQnCount() == 2) {
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
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        ans1 = 0;
        ans2 = 0;
        ivChoice1.setEnabled(true);
        ivChoice2.setEnabled(true);
        if (questionCounter < questionCountTotal) {
            currentQuestion = al.get(questionCounter);

            ivQuestion.setImageResource(currentQuestion.getQnMainImage());
            if (currentQuestion.getQnCount() == 1) {
                ivChoice2.setImageResource(android.R.color.transparent);
                ivChoice2.setEnabled(false);
                if (currentQuestion.getQn1Image() != 0) {
                    ivChoice1.setImageResource(currentQuestion.getQn1Image());
                    numOfAnsForQn1 = currentQuestion.getNumOfQuestions1();
                }
            } else if (currentQuestion.getQnCount() == 2) {
                    ivChoice1.setImageResource(currentQuestion.getQn1Image());
                    numOfAnsForQn1 = currentQuestion.getNumOfQuestions1();
                    ivChoice2.setImageResource(currentQuestion.getQn2Image());
                    numOfAnsForQn2 = currentQuestion.getNumOfQuestions2();

            }

            ivChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choiceSound.start();
                    MyCustomAlertDialog(1,numOfAnsForQn1);
                }
            });
            ivChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    choiceSound.start();
                    MyCustomAlertDialog(2,numOfAnsForQn2);
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
        final Dialog MyDialog = new Dialog(AdvancedQuestionsActivity.this);
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
            } else if (numOfAns == 4) {
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns1Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns1Image2());
                ivUserChoice3.setEnabled(true);
                ivUserChoice3.setImageResource(currentQuestion.getAns1Image3());
                ivUserChoice4.setEnabled(true);
                ivUserChoice4.setImageResource(currentQuestion.getAns1Image4());
            }


        } else if (qnNum == 2) {
            if (numOfAns == 4) {
                ivUserChoice1.setEnabled(true);
                ivUserChoice1.setImageResource(currentQuestion.getAns2Image1());
                ivUserChoice2.setEnabled(true);
                ivUserChoice2.setImageResource(currentQuestion.getAns2Image2());
                ivUserChoice3.setEnabled(true);
                ivUserChoice3.setImageResource(currentQuestion.getAns2Image3());
                ivUserChoice4.setEnabled(true);
                ivUserChoice4.setImageResource(currentQuestion.getAns2Image4());
            }

        }

        ivUserChoice1.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {
                if (numOfAns == 2) {
                    ans1 = 1;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image1());
                } else if (numOfAns == 4) {
                    ans1 = 1;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image1());
                }

            } else if (qnNum == 2) {
                if (numOfAns == 4) {
                    ans2 = 1;
                    ivChoice2.setImageResource(currentQuestion.getAns2Image1());
                }

            }
            MyDialog.cancel();
        });

        ivUserChoice2.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {
                if (numOfAns == 2) {
                    ans1 = 2;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image2());
                }else if (numOfAns == 4) {
                    ans1 = 2;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image2());
                }

            } else if (qnNum == 2) {
                if (numOfAns == 4) {
                    ans2 = 2;
                    ivChoice2.setImageResource(currentQuestion.getAns2Image2());
                }

            }
            MyDialog.cancel();
        });

        ivUserChoice3.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {
                if (numOfAns == 2) {
                    ans1 = 3;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image3());
                } else if (numOfAns == 4) {
                    ans1 = 3;
                    ivChoice1.setImageResource(currentQuestion.getAns1Image3());
                }

            } else if (qnNum == 2) {
                if (numOfAns == 4) {
                    ans2 = 3;
                    ivChoice2.setImageResource(currentQuestion.getAns2Image3());
                }

            }
            MyDialog.cancel();
        });

        ivUserChoice4.setOnClickListener(view -> {
            choiceSound.start();
            if (qnNum == 1) {

                ans1 = 4;
                ivChoice1.setImageResource(currentQuestion.getAns1Image4());


            } else if (qnNum == 2) {

                ans2 = 4;
                ivChoice2.setImageResource(currentQuestion.getAns2Image4());


            }
            MyDialog.cancel();

        });

        MyDialog.show();

    }

    private void finishQuiz() {
        finishSound.start();
        Intent i = new Intent(AdvancedQuestionsActivity.this, ResultActivity.class);
        i.putExtra("score", score);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        backgroundMusic.start();
        backgroundMusic.setLooping(true);
    }
}




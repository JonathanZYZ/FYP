package com.myapplicationdev.android.fyp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Model.QuestionEasy;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    Button btnStart;
    TextView tvQuestionsNumber, tvScore;
    RadioGroup group;
    RadioButton rdReaction_Option1, rdReaction_Option2;
    ImageView ivQuestion, btnExit;
    ArrayList<QuestionEasy> al;
    int questionCounter, questionCountTotal;
    QuestionEasy currentQuestion;
    int score;
    boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        btnStart = findViewById(R.id.btnStart);
        tvQuestionsNumber = findViewById(R.id.tvQuestions_Number);
        tvScore = findViewById(R.id.tvPoint);
        group = findViewById(R.id.group);
        rdReaction_Option1 = findViewById(R.id.radioButtonOption1);
        rdReaction_Option2 = findViewById(R.id.radioButtonOption2);
        ivQuestion = findViewById(R.id.ivQuestions);
        btnExit = findViewById(R.id.btnExit);

        al = new ArrayList<>();
        al.add(new QuestionEasy("basic", "1", R.drawable.question1_basic, R.drawable.question1_basic_incorrect, R.drawable.question1_basic_correct, 2));
        al.add(new QuestionEasy("basic", "2", R.drawable.question2_basic, R.drawable.question2_basic_incorrect, R.drawable.question2_basic_correct, 2));
        al.add(new QuestionEasy("basic", "3", R.drawable.question3_basic, R.drawable.question3_basic_incorrect, R.drawable.question3_basic_correct, 1));
        al.add(new QuestionEasy("basic", "4", R.drawable.question4_basic, R.drawable.question4_basic_incorrect, R.drawable.question4_basic_correct, 1));
        al.add(new QuestionEasy("basic", "5", R.drawable.question5_basic, R.drawable.question5_basic_incorrect, R.drawable.question5_basic_correct, 1));
        al.add(new QuestionEasy("basic", "6", R.drawable.question6_basic, R.drawable.question6_basic_incorrect, R.drawable.question6_basic_correct, 2));
        al.add(new QuestionEasy("basic", "7", R.drawable.question7_basic, R.drawable.question7_basic_incorrect, R.drawable.question7_basic_correct, 2));
        al.add(new QuestionEasy("basic", "8", R.drawable.question8_basic, R.drawable.question8_basic_incorrect, R.drawable.question8_basic_correct, 2));
        al.add(new QuestionEasy("basic", "9", R.drawable.question9_basic, R.drawable.question9_basic_incorrect, R.drawable.question9_basic_correct, 1));

        questionCountTotal = al.size();

        showNextQuestion();

        btnStart.setOnClickListener(view -> {
            //TODO
//            Intent i = new Intent(QuestionsActivity.this, QuestionsAnswerActivity.class);
//            i.putExtra("question","Question 1");
//            startActivity(i);

            if (!answered) {
                if (rdReaction_Option1.isChecked() || rdReaction_Option2.isChecked()) {
                    checkAnswer();
                } else {
                    Toast.makeText(QuestionsActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                }
            } else {
                showNextQuestion();
            }
        });

        btnExit.setOnClickListener(view -> {


            AlertDialog.Builder exitScreen = new AlertDialog.Builder(QuestionsActivity.this);
//                exitScreen.setTitle("");
            exitScreen.setMessage("Are you sure you want to quit? \n Here is your final score: " + score);
            exitScreen.setCancelable(false);


            exitScreen.setPositiveButton("Yes", (dialogInterface, i) -> {

                Intent basicMode_to_main = new Intent(QuestionsActivity.this, MainActivity.class);
//                        basicMode_to_main.putExtra("question","Question 1");
                startActivity(basicMode_to_main);
            });

            exitScreen.setNeutralButton("No", null);
            AlertDialog ShowDialogExit = exitScreen.create();
            ShowDialogExit.show();
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(group.getCheckedRadioButtonId());
        int answer_number = group.indexOfChild(rbSelected) + 1;

        if (answer_number == currentQuestion.getAnswerNum()) {
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(QuestionsActivity.this);
            myBuilder.setTitle("Check Answer");
            myBuilder.setMessage("You selected the correct answer!");
            myBuilder.setCancelable(false);
            myBuilder.setPositiveButton("Dismiss", null);

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();

            score++;
            tvScore.setText("Score: " + score);

        } else {
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(QuestionsActivity.this);
            myBuilder.setTitle("Check Answer");
            myBuilder.setMessage("You selected the wrong answer!");
            myBuilder.setCancelable(false);
            myBuilder.setPositiveButton("Dismiss", null);

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        }

        if (questionCounter < questionCountTotal) {
            btnStart.setText("Next Question");
        } else {
            btnStart.setText("Finish Quiz");
        }
    }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        group.clearCheck();
        if (questionCounter < questionCountTotal) {
            currentQuestion = al.get(questionCounter);

            ivQuestion.setImageResource(currentQuestion.getQuestionsImg());
            rdReaction_Option1.setBackgroundResource(currentQuestion.getMCQoption1Reaction());
//            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(100,100);
//            rdReaction_Option1.setLayoutParams(parms);

            rdReaction_Option2.setBackgroundResource(currentQuestion.getMCQoption2Reaction());


//            ivQuestion.setImageResource();
            questionCounter++;
            tvQuestionsNumber.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            btnStart.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
//        finish();
        Intent finishBasicMode_to_leaderboard = new Intent(QuestionsActivity.this, LeaderboardActivity.class);
        startActivity(finishBasicMode_to_leaderboard);
    }
}
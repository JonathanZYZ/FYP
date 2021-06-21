package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.myapplicationdev.android.fyp.Model.Question;
import com.myapplicationdev.android.fyp.Util.DBHelper;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    Button btnStart;
    TextView tvQuestionsNumber;
    RadioGroup group;
    RadioButton rdReaction_Option1, rdReaction_Option2;
    ImageView ivQuestion;

    ArrayList<Question> al;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        btnStart = findViewById(R.id.btnStart);
        tvQuestionsNumber = findViewById(R.id.tvQuestion);
        group = findViewById(R.id.group);
        rdReaction_Option1 = findViewById(R.id.radioButtonOption1);
        rdReaction_Option2 = findViewById(R.id.radioButtonOption2);
        ivQuestion = findViewById(R.id.ivQuestions);

        DBHelper dbh = new DBHelper(this);
        al = dbh.getAllQuestions();
        questionCountTotal = al.size();

        showNextQuestion();

        btnStart.setOnClickListener(view -> {
            //TODO
//            Intent i = new Intent(QuestionsActivity.this, QuestionsAnswerActivity.class);
//            i.putExtra("question","Question 1");
//            startActivity(i);

            if(!answered) {
                if(rdReaction_Option1.isChecked() || rdReaction_Option2.isChecked()) {
                    checkAnswer();
                } else {
                    Toast.makeText(QuestionsActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                }
            } else {
                showNextQuestion();
            }
        });
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(group.getCheckedRadioButtonId());
        int answer_number = group.indexOfChild(rbSelected) + 1;

        if(answer_number == currentQuestion.getAnswerNum()) {
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(QuestionsActivity.this);
            myBuilder.setTitle("Check Answer");
            myBuilder.setMessage("You selected the correct answer!");
            myBuilder.setCancelable(false);
            myBuilder.setPositiveButton("Dismiss", null);

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();

        } else {
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(QuestionsActivity.this);
            myBuilder.setTitle("Check Answer");
            myBuilder.setMessage("You selected the wrong answer!");
            myBuilder.setCancelable(false);
            myBuilder.setPositiveButton("Dismiss", null);

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        }

        if(questionCounter < questionCountTotal) {
            btnStart.setText("Next");
        } else {
            btnStart.setText("Finish");
        }
    }
    private void showNextQuestion() {
        group.clearCheck();
        if (questionCounter < questionCountTotal) {
            currentQuestion = al.get(questionCounter);

            ivQuestion.setImageResource(currentQuestion.getQuestionsImg());
            rdReaction_Option1.setText(currentQuestion.getMCQoption1Reaction());
            rdReaction_Option2.setText(currentQuestion.getMCQoption2Reaction());


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
        finish();
    }
}
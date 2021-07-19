package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionsAnswerActivity extends AppCompatActivity {

    ImageView ivOption1, ivOption2;
    TextView tvQuestions;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_answer);

        ivOption1 = findViewById(R.id.ivOption1);
        ivOption2 = findViewById(R.id.ivOption2);
        tvQuestions = findViewById(R.id.tvQuestions);
        Intent intent = getIntent();
        String questionNum = intent.getStringExtra("question");
        tvQuestions.setText(questionNum + "  \n What is the correct answer?");

        ivOption1.setOnClickListener(view -> {

            //TODO
            Intent i = new Intent(QuestionsAnswerActivity.this, QuestionCorrectActivity.class);
            i.putExtra("mode","correct");
            startActivity(i);
        });

        ivOption2.setOnClickListener(view -> {

            //TODO
            Intent i = new Intent(QuestionsAnswerActivity.this, QuestionWrongActivity.class);
            i.putExtra("mode","Incorrect");
            startActivity(i);
        });
    }
}
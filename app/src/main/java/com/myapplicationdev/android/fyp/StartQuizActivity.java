package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StartQuizActivity extends AppCompatActivity {
    TextView tvMode, tvDesc;
    Button btnStart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        tvMode = findViewById(R.id.tvMode);
        tvDesc = findViewById(R.id.tvDesc);
        btnStart = findViewById(R.id.btnStartGame);

        Intent i = getIntent();
        String mode = i.getStringExtra("mode");

        if (mode.equalsIgnoreCase("easy")) {
            tvMode.setText("Easy");
            tvDesc.setText("The quiz contains 9 questions and there is no time limit");
            btnStart.setOnClickListener(view -> {
                //TODO
                Intent i1 = new Intent(StartQuizActivity.this, QuestionsActivity.class);
                i1.putExtra("mode", "easy");
                startActivity(i1);
            });

        } else if (mode.equalsIgnoreCase("intermediate")) {
            tvMode.setText("Intermediate");
            tvDesc.setText("The quiz contains 9 questions and there has a time limit");
            btnStart.setOnClickListener(view -> {
                //TODO
                Intent i12 = new Intent(StartQuizActivity.this, QuestionsActivity.class);
                i12.putExtra("mode", "intermediate");
                startActivity(i12);
            });
        } else if (mode.equalsIgnoreCase("advanced")) {
            tvMode.setText("Advanced");
            tvDesc.setText("The quiz contains 9 questions and there has a time limit");
            btnStart.setOnClickListener(view -> {
                //TODO
                Intent i13 = new Intent(StartQuizActivity.this, QuestionsActivity.class);
                i13.putExtra("mode", "advanced");
                startActivity(i13);
            });
        }
    }
}
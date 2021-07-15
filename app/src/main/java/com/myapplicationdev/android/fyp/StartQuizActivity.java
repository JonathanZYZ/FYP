package com.myapplicationdev.android.fyp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Questions.AdvancedQuestionsActivity;
import com.myapplicationdev.android.fyp.Questions.EasyQuestionsActivity;
import com.myapplicationdev.android.fyp.Questions.IntermediateQuestionsActivity;

import java.util.concurrent.ConcurrentHashMap;

public class StartQuizActivity extends AppCompatActivity {
    TextView tvMode, tvDesc;
    Button btnStart;
    Vibrator v;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);


        tvMode = findViewById(R.id.tvMode);
        tvDesc = findViewById(R.id.tvDesc);
        btnStart = findViewById(R.id.btnStartGame);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Intent i = getIntent();
        String mode = i.getStringExtra("mode");

        if (mode.equalsIgnoreCase("easy")) {
            tvMode.setText("Easy");
            tvDesc.setText("The quiz contains 18 questions and there is no time limit");
            btnStart.setOnClickListener(view -> {
                v.vibrate(50);
                //TODO
                Intent intent1 = new Intent(StartQuizActivity.this, EasyQuestionsActivity.class);
                intent1.putExtra("mode", "easy");
                startActivity(intent1);
            });


        } else if (mode.equalsIgnoreCase("intermediate")) {
            tvMode.setText("Intermediate");
            tvDesc.setText("The quiz contains 14 questions and there is no time limit");
            btnStart.setOnClickListener(view -> {
                v.vibrate(50);
                //TODO
                Intent intent2 = new Intent(StartQuizActivity.this, IntermediateQuestionsActivity.class);
                intent2.putExtra("mode", "intermediate");
                startActivity(intent2);
            });

        } else if (mode.equalsIgnoreCase("advanced")) {
            tvMode.setText("Advanced");
            tvDesc.setText("The quiz contains 19 questions and there is no time limit");
            btnStart.setOnClickListener(view -> {
                v.vibrate(50);
                //TODO
                Intent intent3 = new Intent(StartQuizActivity.this, AdvancedQuestionsActivity.class);
                intent3.putExtra("mode", "advanced");
                startActivity(intent3);
            });
        }
    }
}
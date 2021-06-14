package com.myapplicationdev.android.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Questions extends AppCompatActivity {

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(view -> {
            //TODO
            Intent i = new Intent(Questions.this,Questions_answer.class);
            i.putExtra("question","Question 1");
            startActivity(i);
        });
    }
}
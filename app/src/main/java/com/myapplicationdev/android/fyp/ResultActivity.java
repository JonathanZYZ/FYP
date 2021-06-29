package com.myapplicationdev.android.fyp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.Util.DBHelper;

public class ResultActivity extends AppCompatActivity {


    TextView textViewHeading, textViewMyScore, textViewHighestScore;
    Button btnPlayAgain, btnQuitGame;
    int myScore;
    SharedPreferences sharedPreferences;
    DBHelper dbh = new DBHelper(ResultActivity.this);

    @SuppressLint({"SetTextI18n", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewHeading = findViewById(R.id.textViewHeading);
        textViewMyScore = findViewById(R.id.textViewMyScore);
        textViewHighestScore = findViewById(R.id.textViewHighestScore);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnQuitGame = findViewById(R.id.btnQuitGame);

        myScore = getIntent().getIntExtra("score", 0);
        textViewMyScore.setText("Your Score : " + myScore);


        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore", 0);


        if (myScore >= highestScore) {
            sharedPreferences.edit().putInt("highestScore", myScore).apply();
            textViewHighestScore.setText("Highest Score : " + myScore);
            textViewHeading.setText("Congratulations. The new high score. Do you want to get better scores?");
        } else {
            textViewHighestScore.setText("Highest Score : " + highestScore);
            if ((highestScore - myScore) > 10) {
                textViewHeading.setText("xxxx");
            }
            if ((highestScore - myScore) > 3 && (highestScore - myScore) <= 10) {
                textViewHeading.setText("xxxxx");
            }
            if ((highestScore - myScore) <= 3) {
                textViewHeading.setText("xxxxx");
            }
        }


        btnPlayAgain.setOnClickListener(v -> {

            Intent intent = new Intent(ResultActivity.this, MainActivity.class);


            startActivity(intent);
            finish();



        });

        btnQuitGame.setOnClickListener(v -> {

            moveTaskToBack(true);
            // Kill the process with the given PID.
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);








        });


    }
}
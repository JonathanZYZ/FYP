package com.myapplicationdev.android.fyp.Questions;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.MainActivity;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Scoreboards.ShowScoreboardActivity;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ResultActivity extends AppCompatActivity {

    LinearLayout linearLayoutBackground;
    TextView textViewHeading, textViewMyScore, textViewHighestScore, tvScoreOverview;
    Button btnPlayAgain, btnQuitGame, btnScoreboard;
    String username, difficulty;
    SharedPreferences sharedPreferences;
    DBHelper dbh = new DBHelper(ResultActivity.this);
    int myScore, highestScore;

    @SuppressLint({"SetTextI18n", "CutPasteId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        linearLayoutBackground = findViewById(R.id.linearLayoutBackground);
        textViewHeading = findViewById(R.id.textViewHeading);
        textViewMyScore = findViewById(R.id.textViewMyScore);
        textViewHighestScore = findViewById(R.id.textViewHighestScore);
        tvScoreOverview = findViewById(R.id.textViewScoreOverview);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnQuitGame = findViewById(R.id.btnQuitGame);
        btnScoreboard = findViewById(R.id.btnScoreBoard);

        // getting user data from intents
        username = getIntent().getStringExtra("username");
        difficulty = getIntent().getStringExtra("difficulty");
        myScore = getIntent().getIntExtra("scoreTotal", 0);

        // TODO: the total score obtained from all activities completed
        String score = String.valueOf(myScore);
        int scoreEasy = getIntent().getIntExtra("scoreEasy", 0);
        int scoreInter = getIntent().getIntExtra("scoreIntermediate", 0);
        int scoreAdv = getIntent().getIntExtra("scoreAdv", 0);

        String myScoreOverview = String.format("Score Overview: \n%s: %s", difficulty, score);
        tvScoreOverview.setText(myScoreOverview);


        // TODO: defining the time for the user's playing date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        TimeZone tz = TimeZone.getTimeZone("Asia/Singapore");
        sdf.setTimeZone(tz);

        Date date = new java.util.Date();
        String strDate = sdf.format(date);

        dbh = new DBHelper(ResultActivity.this);

        // TODO:  adding data into SQlite DB
        long inserted_id = dbh.insertScoreBoard(username, score, difficulty, strDate);

        dbh.close();

        if (inserted_id != -1) {
            Toast.makeText(ResultActivity.this,
                    "Inserted successful",
                    Toast.LENGTH_SHORT).show();
        }

        textViewMyScore.setText("Your Score : " + myScore);

        textViewHeading.setText("Good Job!");

        sharedPreferences = getSharedPreferences("Score", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("highestScore")) {
            highestScore = sharedPreferences.getInt("highestScore", 0);
        } else {
            highestScore = 0;
        }


        if (myScore >= highestScore) {
            sharedPreferences.edit().putInt("highestScore", myScore).apply();
            textViewHighestScore.setText("Highest Score : " + myScore);
            textViewHeading.setText("Congratulations. The new high score. Do you want to get better scores?");

        } else {

            textViewHighestScore.setText("Highest Score : " + highestScore);
            if ((highestScore - myScore) > 10) {
                textViewHeading.setText(username);
            }
            if ((highestScore - myScore) > 3 && (highestScore - myScore) <= 10) {
                textViewHeading.setText(username);
            }
            if ((highestScore - myScore) <= 3) {

                textViewHighestScore.setText("Lowest Score : " + myScore);
                textViewMyScore.setBackgroundColor(Color.parseColor("#FF3D3D"));
                textViewHeading.setText("You're capable of so much more.");
                tvScoreOverview.setBackgroundColor(Color.parseColor("#F72119"));
                linearLayoutBackground.setBackgroundColor(Color.parseColor("#FF6868"));

            }
        }


        btnPlayAgain.setOnClickListener(v -> {

            Intent intent = new Intent();

            if (difficulty.equalsIgnoreCase("easy")) {
                intent = new Intent(ResultActivity.this, EasyQuestionsActivity.class);
            } else if (difficulty.equalsIgnoreCase("intermediate")) {
                intent = new Intent(ResultActivity.this, IntermediateQuestionsActivity.class);
            } else if (difficulty.equalsIgnoreCase("advanced")) {
                intent = new Intent(ResultActivity.this, AdvancedQuestionsActivity.class);
            }

            startActivity(intent);
            finish();


        });
        btnScoreboard.setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this, ShowScoreboardActivity.class);
            startActivity(intent);
            finish();
        });

        btnQuitGame.setOnClickListener(v -> {
            // TODO: after users quits the game after playing , they would lead to the DifficultySectionActivity Activity
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });


    }

}

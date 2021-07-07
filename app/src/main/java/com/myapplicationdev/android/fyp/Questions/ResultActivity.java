package com.myapplicationdev.android.fyp.Questions;

import java.text.SimpleDateFormat;

import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.DifficultySectionActivity;
import com.myapplicationdev.android.fyp.MainActivity;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Scoreboards.ScoreboardActivity;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

public class ResultActivity extends AppCompatActivity {


    EditText editTextDialogUserName;

    TextView textViewHeading, textViewMyScore, textViewHighestScore, textViewDialogScore, textViewDialogDatePlayed;
    Button btnPlayAgain, btnQuitGame,btnScoreboard;
    int myScore;
    String username;
    SharedPreferences sharedPreferences;
    DBHelper dbh = new DBHelper(ResultActivity.this);
    MediaPlayer mediaPlayer;
    String date;


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
        btnScoreboard = findViewById(R.id.btnScoreBoard);

        // TODO: the total score obtained from all activities completed
        myScore = getIntent().getIntExtra("score", 0);
        String score = String.valueOf(myScore);
        username = getIntent().getStringExtra("username");
        // TODO: Init popup dialog view and it's ui controls.
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.format(new Date());

        dbh = new DBHelper(ResultActivity.this);


        long inserted_id = dbh.insertScoreBoard(username, score, date);

        dbh.close();

        if (inserted_id != -1) {
            Toast.makeText(ResultActivity.this, "Insert successful",
                    Toast.LENGTH_SHORT).show();
        }

        textViewMyScore.setText("Your Score : " + myScore);

        textViewHeading.setText("Good Job!");
        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore", 0);


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
                textViewHeading.setText(username);
            }
        }


//        UserInputDialog();

        btnPlayAgain.setOnClickListener(v -> {
            String difficulty = getIntent().getStringExtra("difficulty");
            Intent intent = new Intent();
            if (difficulty.equalsIgnoreCase("easy")) {
                intent = new Intent(ResultActivity.this, EasyQuestionsActivity.class);
            }else if (difficulty.equalsIgnoreCase("intermediate")) {
                intent = new Intent(ResultActivity.this, IntermediateQuestionsActivity.class);
            }else if (difficulty.equalsIgnoreCase("advanced")) {
                intent = new Intent(ResultActivity.this, AdvancedQuestionsActivity.class);
            }
            //Intent intent = new Intent(ResultActivity.this, DifficultySectionActivity.class);
            startActivity(intent);
            finish();


        });
        btnScoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, ScoreboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnQuitGame.setOnClickListener(v -> {
            // TODO: after users quits the game after playing , they would lead to the LeaderBoard Activity
            Intent intent = new Intent(ResultActivity.this, DifficultySectionActivity.class);
            startActivity(intent);
            finish();
        });


    }

    /* Initialize popup dialog view and ui controls in the popup dialog. */

//    public void UserInputDialog(){
//        Dialog MyDialog = new Dialog(this);
//        // Set title, icon, can not cancel properties.
//        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        // Set the inflated layout view object to the AlertDialog builder.
//        MyDialog.setContentView(R.layout.popup_input_name_dialog);
//
//
//        EditText dialogUserName = MyDialog.findViewById(R.id.dialogUserName);
//        TextView dialogScore = MyDialog.findViewById(R.id.dialogScore);
//        TextView dialogDatePlayed = MyDialog.findViewById(R.id.dialogDatePlayed);
//        Button cancelUserData = MyDialog.findViewById(R.id.buttonCancelUserData);
//        Button saveUserData = MyDialog.findViewById(R.id.buttonSaveUserData);


//        dialogScore.setText(myScore);
//        dialogDatePlayed.setText(date);
//
//        saveUserData.setOnClickListener(v1 -> {
//
//            // Getting data
//            String username = dialogUserName.getText().toString();
//            String score = dialogScore.getText().toString();
//            String playedDate = dialogDatePlayed.getText().toString();
//
//
//            // TODO:  adding data into SQlite DB
//            DBHelper dbh = new DBHelper(ResultActivity.this);
//
//
//            long inserted_id = dbh.insertScoreBoard(username, score, playedDate);
//
//            dbh.close();
//
//            if (inserted_id != -1) {
//                Toast.makeText(ResultActivity.this, "Insert successful",
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
//        cancelUserData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                MyDialog.dismiss();
//                Intent i = new Intent(ResultActivity.this, ScoreboardActivity.class);
//                startActivity(i);
//            }
//        });
//
//        MyDialog.show();
    }
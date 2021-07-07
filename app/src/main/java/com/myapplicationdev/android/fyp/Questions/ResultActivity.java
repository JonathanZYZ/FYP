package com.myapplicationdev.android.fyp.Questions;

import java.text.SimpleDateFormat;

import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.myapplicationdev.android.fyp.MainActivity;
import com.myapplicationdev.android.fyp.R;
import com.myapplicationdev.android.fyp.Scoreboards.ScoreboardActivity;
import com.myapplicationdev.android.fyp.Utilities.DBHelper;

public class ResultActivity extends AppCompatActivity {


    EditText editTextDialogUserName;

    View popupInputDialogView;
    TextView textViewHeading, textViewMyScore, textViewHighestScore, textViewDialogScore, textViewDialogDatePlayed;
    Button btnPlayAgain, btnQuitGame, buttonCancelUserData, buttonSaveUserData;
    int myScore;
    SharedPreferences sharedPreferences;
    DBHelper dbh = new DBHelper(ResultActivity.this);
    MediaPlayer mediaPlayer;


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

        // TODO: the total score obtained from all activities completed
        myScore = getIntent().getIntExtra("score", 0);

        // TODO: Init popup dialog view and it's ui controls.
        initPopupViewControls();

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


// TODO: Create a AlertDialog Builder.
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResultActivity.this);
            // Set title, icon, can not cancel properties.
            alertDialogBuilder.setTitle("Finalizing data to be stored in the Scoreboard");
            alertDialogBuilder.setIcon(R.drawable.new_logo);
            alertDialogBuilder.setCancelable(false);

            // Set the inflated layout view object to the AlertDialog builder.
            alertDialogBuilder.setView(popupInputDialogView);

            // Create AlertDialog and show.
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();


            // setting Data for popup dialog textViewDialogScore
            textViewDialogScore.setText(myScore);

            // Create SimpleDateFormat and convert current date into SQL format string
            @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(new Date());

            // setting Data for popup dialog textViewDialogScore
            textViewDialogDatePlayed.setText(date);


// TODO: when user click the save user data button in the popup dialog.
            buttonSaveUserData.setOnClickListener(v1 -> {

                // Getting data
                String username = editTextDialogUserName.getText().toString();
                String score = textViewDialogDatePlayed.getText().toString();
                String playedDate = textViewDialogDatePlayed.getText().toString();


                // TODO:  adding data into SQlite DB
                DBHelper dbh = new DBHelper(ResultActivity.this);


                long inserted_id = dbh.insertScoreBoard(username, score, playedDate);

                dbh.close();

                if (inserted_id != -1) {
                    Toast.makeText(ResultActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

            });
            buttonCancelUserData.setOnClickListener(view1 -> alertDialog.cancel());


            // TODO: after users quits the game after playing , they would lead to the LeaderBoard Activity
            Intent i = new Intent(ResultActivity.this, ScoreboardActivity.class);
            startActivity(i);
            mediaPlayer.start();
        });


    }

    /* Initialize popup dialog view and ui controls in the popup dialog. */
    @SuppressLint("InflateParams")
    void initPopupViewControls() {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(ResultActivity.this);

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.popup_input_name_dialog, null);

        // Get user input edittext and button ui controls in the popup dialog.
        editTextDialogUserName = popupInputDialogView.findViewById(R.id.editTextDialogUserName);
        textViewDialogScore = popupInputDialogView.findViewById(R.id.textViewDialogScore);
        textViewDialogScore = popupInputDialogView.findViewById(R.id.textViewDialogScore);
        textViewDialogDatePlayed = popupInputDialogView.findViewById(R.id.textViewDialogDatePlayed);
        buttonCancelUserData = popupInputDialogView.findViewById(R.id.buttonCancelUserData);
        buttonSaveUserData = popupInputDialogView.findViewById(R.id.buttonSaveUserData);

        // Display values from the main activity list view in user input edittext.
        //initEditTextUserDataInPopupDialog();
    }
}